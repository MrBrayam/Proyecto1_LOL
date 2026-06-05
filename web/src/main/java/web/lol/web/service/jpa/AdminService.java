package web.lol.web.service.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lol.web.model.Admin;
import web.lol.web.repository.AdminRepository;
import web.lol.web.service.IAdminService;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> buscarTodos() {
        return adminRepository.findAll();
    }

    public void guardar(Admin admin) {
        adminRepository.save(admin);
    }

    public void modificar(Admin admin) {
        adminRepository.save(admin);
    }

    public Optional<Admin> buscarId(Integer id) {
        return adminRepository.findById(id);
    }

    public void eliminar(Integer id) {
        adminRepository.deleteById(id);
    }

    public Optional<Admin> validarCredenciales(String nombre, String contrasena) {
        try {
            System.out.println("=== VALIDANDO CREDENCIALES ===");
            System.out.println("Usuario: " + nombre);
            System.out.println("Contraseña: " + contrasena);

            Optional<Admin> resultado = adminRepository.validarCredenciales(nombre, contrasena);

            if (resultado.isPresent()) {
                System.out.println("✅ Credenciales válidas para: " + resultado.get().getNombre());
            } else {
                System.out.println("❌ Credenciales inválidas para: " + nombre);

                Optional<Admin> usuarioExiste = adminRepository.findByNombreNative(nombre);
                if (usuarioExiste.isPresent()) {
                    Admin admin = usuarioExiste.get();
                    System.out.println("Usuario encontrado: " + admin.getNombre() +
                                     ", Estado: " + admin.getEstado() +
                                     ", Contraseña en BD: " + admin.getContrasena());
                } else {
                    System.out.println("Usuario no encontrado en la base de datos");
                }
            }

            return resultado;
        } catch (Exception e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Admin> obtenerTodosAdmins() {
        try {
            return adminRepository.findAllForAdmin().stream()
                .filter(a -> a.getEstado() == 1)
                .sorted((a1, a2) -> a1.getNombre().compareTo(a2.getNombre()))
                .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener administradores: " + e.getMessage());
            return List.of();
        }
    }

    public List<Admin> obtenerTodosAdminsParaAdmin() {
        try {
            return adminRepository.findAllForAdmin();
        } catch (Exception e) {
            System.err.println("Error al obtener administradores para admin: " + e.getMessage());
            return List.of();
        }
    }

    public Admin guardarAdmin(Admin admin) {
        try {
            Optional<Admin> existente = adminRepository.findByNombreNative(admin.getNombre());
            if (existente.isPresent() && !existente.get().getIdAdmin().equals(admin.getIdAdmin())) {
                throw new RuntimeException("Ya existe un administrador con ese nombre");
            }

            return adminRepository.save(admin);
        } catch (Exception e) {
            System.err.println("Error al guardar administrador: " + e.getMessage());
            throw new RuntimeException("Error al guardar administrador: " + e.getMessage());
        }
    }

    public Admin crearAdmin(Admin admin) {
        try {
            if (adminRepository.findByNombreNative(admin.getNombre()).isPresent()) {
                throw new RuntimeException("Ya existe un administrador con ese nombre");
            }

            admin.setEstado(1);
            return adminRepository.save(admin);
        } catch (Exception e) {
            System.err.println("Error al crear administrador: " + e.getMessage());
            throw new RuntimeException("Error al crear administrador: " + e.getMessage());
        }
    }

    public boolean activarAdmin(Integer id) {
        try {
            int resultado = adminRepository.activarAdmin(id);
            return resultado > 0;
        } catch (Exception e) {
            System.err.println("Error al activar administrador: " + e.getMessage());
            return false;
        }
    }

    public boolean desactivarAdmin(Integer id) {
        try {
            int resultado = adminRepository.desactivarAdmin(id);
            return resultado > 0;
        } catch (Exception e) {
            System.err.println("Error al desactivar administrador: " + e.getMessage());
            return false;
        }
    }

    public boolean existeAlMenosUnAdminActivo() {
        try {
            return adminRepository.findAllForAdmin().stream()
                .anyMatch(a -> a.getEstado() == 1);
        } catch (Exception e) {
            System.err.println("Error al verificar administradores activos: " + e.getMessage());
            return false;
        }
    }

    public boolean nombreDisponible(String nombre, Integer idExcluir) {
        Optional<Admin> existente = adminRepository.findByNombreNative(nombre);
        return existente.isEmpty() ||
               (idExcluir != null && existente.get().getIdAdmin().equals(idExcluir));
    }

    public Optional<Admin> buscarPorNombre(String nombre) {
        return adminRepository.findByNombreNative(nombre);
    }
}
