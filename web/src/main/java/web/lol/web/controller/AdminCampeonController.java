package web.lol.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.lol.web.model.Campeon;
import web.lol.web.repository.CampeonRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/campeones")
public class AdminCampeonController {

    @Autowired
    private CampeonRepository campeonRepository;

    @GetMapping
    public String listarCampeones(Model model) {
        List<Campeon> campeones = campeonRepository.findAllForAdmin();
        model.addAttribute("campeones", campeones);
        return "admin/campeones/index";
    }

    @GetMapping("/form")
    public String formularioNuevo(Model model) {
        model.addAttribute("campeon", new Campeon());
        return "admin/campeones/form";
    }

    @PostMapping("/create")
    public String crear(
            @RequestParam("nombreCampeon") String nombreCampeon,
            @RequestParam("descripcionCampeon") String descripcionCampeon
    ) {
        System.out.println("=== CREANDO NUEVO CAMPEÓN ===");
        System.out.println("Nombre: " + nombreCampeon);
        System.out.println("Descripción: " + descripcionCampeon);

        try {
            Campeon campeon = new Campeon();
            // El ID se generará automáticamente por la base de datos
            campeon.setNombreCampeon(nombreCampeon);
            campeon.setDescripcionCampeon(descripcionCampeon);
            campeon.setEstado(1);

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
    public String formularioEditar(@PathVariable Integer id, Model model) {
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
            @RequestParam("descripcionCampeon") String descripcionCampeon
    ) {
        try {
            Optional<Campeon> opt = campeonRepository.findById(idCampeon);
            if (opt.isPresent()) {
                Campeon campeon = opt.get();
                campeon.setNombreCampeon(nombreCampeon);
                campeon.setDescripcionCampeon(descripcionCampeon);
                
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
    public String desactivar(@PathVariable Integer id) {
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
    public String activar(@PathVariable Integer id) {
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
}