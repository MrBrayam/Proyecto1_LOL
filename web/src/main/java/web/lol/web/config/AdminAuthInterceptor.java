package web.lol.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import web.lol.web.model.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        
        // Permitir acceso a login y logout sin verificación
        if (requestUri.equals("/admin/login") || requestUri.equals("/admin/logout")) {
            return true;
        }
        
        // Para todas las demás rutas /admin/*, verificar sesión
        if (requestUri.startsWith("/admin/")) {
            HttpSession session = request.getSession(false);
            
            if (session == null || session.getAttribute("adminLogueado") == null) {
                // No hay sesión válida, redirigir a login
                System.out.println("Acceso no autorizado a: " + requestUri + " - Redirigiendo a login");
                response.sendRedirect("/admin/login");
                return false;
            }
            
            // Verificar que el admin en sesión esté activo
            Admin admin = (Admin) session.getAttribute("adminLogueado");
            if (admin.getEstado() == 0) {
                // Admin desactivado, invalidar sesión
                System.out.println("Admin desactivado detectado: " + admin.getNombre() + " - Invalidando sesión");
                session.invalidate();
                response.sendRedirect("/admin/login?error=account_disabled");
                return false;
            }
            
            System.out.println("Acceso autorizado a: " + requestUri + " por: " + admin.getNombre());
        }
        
        return true;
    }
}