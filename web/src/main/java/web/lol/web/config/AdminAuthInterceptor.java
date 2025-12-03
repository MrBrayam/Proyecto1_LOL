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
        
        if (requestUri.equals("/admin/login") || requestUri.equals("/admin/logout")) {
            return true;
        }

        if (requestUri.startsWith("/admin/")) {
            HttpSession session = request.getSession(false);
            
            if (session == null || session.getAttribute("adminLogueado") == null) {
                System.out.println("Acceso no autorizado a: " + requestUri + " - Redirigiendo a login");
                response.sendRedirect("/admin/login");
                return false;
            }
            
            Admin admin = (Admin) session.getAttribute("adminLogueado");
            if (admin.getEstado() == 0) {
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