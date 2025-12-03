package web.lol.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.lol.web.model.Admin;
import web.lol.web.service.AdminService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    
    @Autowired
    private AdminService adminService;
    
    // Mostrar página de login
    @GetMapping("/login")
    public String mostrarLogin(Model model, HttpSession session, 
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        
        // Si ya está logueado, redirigir al dashboard
        Admin adminLogueado = (Admin) session.getAttribute("adminLogueado");
        if (adminLogueado != null) {
            return "redirect:/admin/dashboard";
        }
        
        // Agregar mensajes de error o logout
        if ("true".equals(error)) {
            model.addAttribute("error", "Credenciales incorrectas. Intente nuevamente.");
        } else if ("account_disabled".equals(error)) {
            model.addAttribute("error", "Su cuenta ha sido deshabilitada. Contacte al administrador.");
        }
        
        if (logout != null) {
            model.addAttribute("mensaje", "Sesión cerrada correctamente.");
        }
        
        return "admin/login";
    }
    
    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombre, 
                               @RequestParam String contrasena,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        try {
            Optional<Admin> adminOpt = adminService.validarCredenciales(nombre, contrasena);
            
            if (adminOpt.isPresent()) {
                // Login exitoso
                Admin admin = adminOpt.get();
                session.setAttribute("adminLogueado", admin);
                session.setMaxInactiveInterval(30 * 60); // 30 minutos
                
                System.out.println("Login exitoso para: " + admin.getNombre());
                return "redirect:/admin/dashboard";
            } else {
                // Credenciales incorrectas
                System.out.println("Login fallido para: " + nombre);
                return "redirect:/admin/login?error=true";
            }
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error interno del servidor");
            return "redirect:/admin/login?error=true";
        }
    }
    
    // Dashboard principal del admin
    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model, HttpSession session) {
        // Verificar si está logueado
        Admin adminLogueado = (Admin) session.getAttribute("adminLogueado");
        if (adminLogueado == null) {
            return "redirect:/admin/login";
        }
        
        model.addAttribute("admin", adminLogueado);
        model.addAttribute("title", "Panel de Administración");
        model.addAttribute("description", "Gestiona campeones y administradores");
        
        return "admin/dashboard";
    }
    
    // Redirigir /admin a /admin/dashboard (con verificación de sesión)
    @GetMapping("")
    public String adminRoot(HttpSession session) {
        Admin adminLogueado = (Admin) session.getAttribute("adminLogueado");
        if (adminLogueado == null) {
            return "redirect:/admin/login";
        }
        return "redirect:/admin/dashboard";
    }
    
    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Admin admin = (Admin) session.getAttribute("adminLogueado");
            if (admin != null) {
                System.out.println("Logout para: " + admin.getNombre());
            }
            
            session.removeAttribute("adminLogueado");
            session.invalidate();
            
            redirectAttributes.addFlashAttribute("mensaje", "Sesión cerrada correctamente");
            return "redirect:/admin/login?logout=true";
        } catch (Exception e) {
            System.err.println("Error en logout: " + e.getMessage());
            return "redirect:/admin/login";
        }
    }
    
    // Endpoint para verificar sesión (AJAX)
    @GetMapping("/verificar-sesion")
    @ResponseBody
    public boolean verificarSesion(HttpSession session) {
        Admin adminLogueado = (Admin) session.getAttribute("adminLogueado");
        return adminLogueado != null;
    }
}