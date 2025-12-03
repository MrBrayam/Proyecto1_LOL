package web.lol.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.lol.web.model.Admin;
import web.lol.web.service.AdminService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/admins")
public class AdminManagementController {
    
    @Autowired
    private AdminService adminService;
    
    // Verificar sesión antes de cada método
    private boolean verificarSesion(HttpSession session) {
        return session.getAttribute("adminLogueado") != null;
    }
    
    // Listar todos los administradores
    @GetMapping
    public String listarAdmins(Model model, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            List<Admin> admins = adminService.obtenerTodosAdminsParaAdmin();
            Admin adminLogueado = (Admin) session.getAttribute("adminLogueado");
            
            model.addAttribute("admins", admins);
            model.addAttribute("adminLogueado", adminLogueado);
            model.addAttribute("title", "Gestionar Administradores");
            
            return "admin/admins/index";
        } catch (Exception e) {
            System.err.println("Error al listar administradores: " + e.getMessage());
            model.addAttribute("error", "Error al cargar los administradores");
            return "admin/dashboard";
        }
    }
    
    // Mostrar formulario para nuevo administrador
    @GetMapping("/form")
    public String mostrarFormularioNuevo(Model model, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", new Admin());
        model.addAttribute("title", "Nuevo Administrador");
        return "admin/admins/form";
    }
    
    // Mostrar formulario para editar administrador
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            Optional<Admin> adminOpt = adminService.obtenerAdminPorId(id);
            if (adminOpt.isPresent()) {
                model.addAttribute("admin", adminOpt.get());
                model.addAttribute("title", "Editar Administrador");
                return "admin/admins/form";
            } else {
                model.addAttribute("error", "Administrador no encontrado");
                return "redirect:/admin/admins";
            }
        } catch (Exception e) {
            System.err.println("Error al obtener administrador: " + e.getMessage());
            model.addAttribute("error", "Error al cargar el administrador");
            return "redirect:/admin/admins";
        }
    }
    
    // Crear nuevo administrador
    @PostMapping("/create")
    public String crearAdmin(@ModelAttribute Admin admin, 
                            @RequestParam(value = "confirmarContrasena", required = false) String confirmarContrasena,
                            HttpSession session, 
                            RedirectAttributes redirectAttributes) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            // Validar confirmación de contraseña
            if (!admin.getContrasena().equals(confirmarContrasena)) {
                redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
                return "redirect:/admin/admins/form";
            }
            
            adminService.crearAdmin(admin);
            redirectAttributes.addFlashAttribute("mensaje", "Administrador creado exitosamente");
            return "redirect:/admin/admins";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/admins/form";
        } catch (Exception e) {
            System.err.println("Error al crear administrador: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error al crear el administrador");
            return "redirect:/admin/admins/form";
        }
    }
    
    // Actualizar administrador
    @PostMapping("/update")
    public String actualizarAdmin(@ModelAttribute Admin admin, 
                                 @RequestParam(value = "confirmarContrasena", required = false) String confirmarContrasena,
                                 HttpSession session, 
                                 RedirectAttributes redirectAttributes) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            // Si la contraseña está vacía, mantener la actual
            if (admin.getContrasena() == null || admin.getContrasena().trim().isEmpty()) {
                // Obtener la contraseña actual de la base de datos
                Optional<Admin> adminActualOpt = adminService.obtenerAdminPorId(admin.getIdAdmin());
                if (adminActualOpt.isPresent()) {
                    admin.setContrasena(adminActualOpt.get().getContrasena());
                }
            } else {
                // Validar confirmación de contraseña
                if (!admin.getContrasena().equals(confirmarContrasena)) {
                    redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
                    return "redirect:/admin/admins/editar/" + admin.getIdAdmin();
                }
            }
            
            adminService.guardarAdmin(admin);
            redirectAttributes.addFlashAttribute("mensaje", "Administrador actualizado exitosamente");
            return "redirect:/admin/admins";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/admins/editar/" + admin.getIdAdmin();
        } catch (Exception e) {
            System.err.println("Error al actualizar administrador: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el administrador");
            return "redirect:/admin/admins/editar/" + admin.getIdAdmin();
        }
    }
    
    // Activar administrador
    @GetMapping("/activar/{id}")
    public String activarAdmin(@PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            boolean resultado = adminService.activarAdmin(id);
            if (resultado) {
                redirectAttributes.addFlashAttribute("mensaje", "Administrador activado exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo activar el administrador");
            }
        } catch (Exception e) {
            System.err.println("Error al activar administrador: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error al activar el administrador");
        }
        
        return "redirect:/admin/admins";
    }
    
    // Desactivar administrador
    @GetMapping("/desactivar/{id}")
    public String desactivarAdmin(@PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!verificarSesion(session)) {
            return "redirect:/admin/login";
        }
        
        try {
            // Verificar que no sea el último admin activo
            Admin adminActual = (Admin) session.getAttribute("adminLogueado");
            if (adminActual.getIdAdmin().equals(id)) {
                redirectAttributes.addFlashAttribute("error", "No puedes desactivar tu propia cuenta");
                return "redirect:/admin/admins";
            }
            
            // Verificar que haya al menos otro admin activo
            List<Admin> adminsActivos = adminService.obtenerTodosAdmins();
            if (adminsActivos.size() <= 1) {
                redirectAttributes.addFlashAttribute("error", "No se puede desactivar el último administrador activo");
                return "redirect:/admin/admins";
            }
            
            boolean resultado = adminService.desactivarAdmin(id);
            if (resultado) {
                redirectAttributes.addFlashAttribute("mensaje", "Administrador desactivado exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo desactivar el administrador");
            }
        } catch (Exception e) {
            System.err.println("Error al desactivar administrador: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error al desactivar el administrador");
        }
        
        return "redirect:/admin/admins";
    }
}