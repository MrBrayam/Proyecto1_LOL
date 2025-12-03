package web.lol.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.lol.web.model.Admin;
import web.lol.web.model.Campeon;
import web.lol.web.repository.CampeonRepository;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/campeones")
public class AdminCampeonController {

    @Autowired
    private CampeonRepository campeonRepository;

    // Directorio donde se guardarán las imágenes
    private static final String UPLOAD_DIR = "src/main/resources/static/img/uploads/";

    // Método para verificar sesión
    private boolean verificarSesion(HttpSession session) {
        return session.getAttribute("adminLogueado") != null;
    }

    @GetMapping
    public String listarCampeones(Model model, HttpSession session,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            // Validar parámetros
            if (page < 0) page = 0;
            if (size <= 0 || size > 100) size = 10;
            
            System.out.println("=== DEBUG PAGINACIÓN ===");
            System.out.println("Page solicitada: " + page);
            System.out.println("Size solicitado: " + size);
            
            // Crear objeto Pageable para paginación
            Pageable pageable = PageRequest.of(page, size);
            
            // Usar consulta nativa que ignora @Where
            Page<Campeon> campeonesPage;
            
            try {
                campeonesPage = campeonRepository.findAllForAdminPaginatedNative(pageable);
                
                System.out.println("=== PAGINACIÓN EXITOSA ===");
                System.out.println("Página: " + page + ", Tamaño: " + size);
                System.out.println("Total elementos: " + campeonesPage.getTotalElements());
                System.out.println("Total páginas: " + campeonesPage.getTotalPages());
                System.out.println("Elementos en esta página: " + campeonesPage.getContent().size());
                System.out.println("Incluye desactivados: " + 
                    campeonesPage.getContent().stream().anyMatch(c -> c.getEstado() == 0));
                
            } catch (Exception e) {
                System.err.println("ERROR en paginación nativa: " + e.getMessage());
                e.printStackTrace();
                
                // Fallback: Lista completa sin paginación
                List<Campeon> campeones = campeonRepository.findAllForAdmin();
                model.addAttribute("campeones", campeones);
                model.addAttribute("totalElements", campeones.size());
                model.addAttribute("currentPage", 0);
                model.addAttribute("totalPages", 1);
                model.addAttribute("size", campeones.size());
                model.addAttribute("error", "Error en la paginación, mostrando todos los elementos");
                
                System.out.println("Fallback activado, mostrando " + campeones.size() + " campeones");
                System.out.println("Incluye desactivados: " + 
                    campeones.stream().anyMatch(c -> c.getEstado() == 0));
                return "admin/campeones/index";
            }
            
            // Agregar todos los atributos al modelo
            model.addAttribute("campeonesPage", campeonesPage);
            model.addAttribute("campeones", campeonesPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", campeonesPage.getTotalPages());
            model.addAttribute("totalElements", campeonesPage.getTotalElements());
            model.addAttribute("size", size);
            
            System.out.println("Total elements: " + campeonesPage.getTotalElements());
            System.out.println("Total pages: " + campeonesPage.getTotalPages());
            System.out.println("Content size: " + campeonesPage.getContent().size());
            System.out.println("=== FIN DEBUG ===");
            
            return "admin/campeones/index";
            
        } catch (Exception e) {
            System.err.println("Error completo al listar campeones: " + e.getMessage());
            e.printStackTrace();
            
            // En caso de error total, usar lista simple sin paginación
            try {
                List<Campeon> campeones = campeonRepository.findAllForAdmin();
                model.addAttribute("campeones", campeones);
                model.addAttribute("totalElements", campeones.size());
                model.addAttribute("currentPage", 0);
                model.addAttribute("totalPages", 1);
                model.addAttribute("size", campeones.size());
                model.addAttribute("error", "Error en la paginación, mostrando todos los elementos");
                
                System.out.println("Fallback activado, mostrando " + campeones.size() + " campeones");
                return "admin/campeones/index";
                
            } catch (Exception e2) {
                System.err.println("Error crítico al listar campeones: " + e2.getMessage());
                e2.printStackTrace();
                model.addAttribute("error", "Error crítico al cargar los campeones: " + e2.getMessage());
                return "admin/dashboard";
            }
        }
    }

    @GetMapping("/form")
    public String formularioNuevo(Model model, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("campeon", new Campeon());
        return "admin/campeones/form";
    }

    @PostMapping("/create")
    public String crear(
            @RequestParam("nombreCampeon") String nombreCampeon,
            @RequestParam("descripcionCampeon") String descripcionCampeon,
            @RequestParam("rutaimg") MultipartFile imagenFile,
            HttpSession session
    ) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        System.out.println("=== CREANDO NUEVO CAMPEÓN ===");
        System.out.println("Nombre: " + nombreCampeon);
        System.out.println("Descripción: " + descripcionCampeon);

        try {
            Campeon campeon = new Campeon();
            campeon.setNombreCampeon(nombreCampeon);
            campeon.setDescripcionCampeon(descripcionCampeon);
            campeon.setEstado(1);

            // Procesar imagen si se subió
            String rutaImagen = guardarImagen(imagenFile, nombreCampeon);
            if (rutaImagen != null) {
                campeon.setRutaimg(rutaImagen);
            }

            campeonRepository.save(campeon);
            System.out.println("Campeón guardado exitosamente con ID: " + campeon.getIdCampeon());
            
            return "redirect:/admin/campeones";

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/admin/campeones/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        Optional<Campeon> campeonOpt = campeonRepository.findById(id);
        if (campeonOpt.isPresent()) {
            model.addAttribute("campeon", campeonOpt.get());
            return "admin/campeones/form";
        }
        return "redirect:/admin/campeones";
    }

    @PostMapping("/update")
    public String actualizar(
            @RequestParam("idCampeon") Integer idCampeon,
            @RequestParam("nombreCampeon") String nombreCampeon,
            @RequestParam("descripcionCampeon") String descripcionCampeon,
            @RequestParam("rutaimg") MultipartFile imagenFile,
            HttpSession session
    ) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            Optional<Campeon> opt = campeonRepository.findById(idCampeon);
            if (opt.isPresent()) {
                Campeon campeon = opt.get();
                campeon.setNombreCampeon(nombreCampeon);
                campeon.setDescripcionCampeon(descripcionCampeon);
                
                // Procesar imagen si se subió una nueva
                String rutaImagen = guardarImagen(imagenFile, nombreCampeon);
                if (rutaImagen != null) {
                    campeon.setRutaimg(rutaImagen);
                }
                
                campeonRepository.save(campeon);
                System.out.println("Campeón actualizado: " + nombreCampeon);
            }
            return "redirect:/admin/campeones";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/campeones";
        }
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Integer id, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            System.out.println("=== DESACTIVANDO CAMPEÓN ===");
            System.out.println("ID: " + id);
            
            int filasAfectadas = campeonRepository.desactivarCampeon(id);
            System.out.println("Filas afectadas: " + filasAfectadas);
            
        } catch (Exception e) {
            System.err.println("ERROR AL DESACTIVAR: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/campeones";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Integer id, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            System.out.println("=== INTENTANDO ACTIVAR CAMPEÓN ===");
            System.out.println("ID: " + id);
            
            campeonRepository.activarCampeon(id);
            System.out.println("Comando de activación enviado");
            
        } catch (Exception e) {
            System.err.println("ERROR AL ACTIVAR: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/campeones?mensaje=activacion-intentada";
    }

    /**
     * Método para guardar imágenes subidas
     */
    private String guardarImagen(MultipartFile file, String nombreCampeon) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            // Crear directorio si no existe
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Obtener extensión del archivo
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            
            // Generar nombre único del archivo
            String fileName = nombreCampeon.replaceAll("[^a-zA-Z0-9]", "_") + "_" + System.currentTimeMillis() + extension;
            
            // Guardar archivo
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Retornar ruta relativa para la web
            String rutaWeb = "/img/uploads/" + fileName;
            System.out.println("Imagen guardada: " + rutaWeb);
            
            return rutaWeb;
            
        } catch (IOException e) {
            System.err.println("Error al guardar imagen: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}