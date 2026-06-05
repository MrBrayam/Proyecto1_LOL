package web.lol.web.service;

import java.util.List;
import java.util.Optional;

import web.lol.web.model.Admin;

public interface IAdminService {
    List<Admin> buscarTodos();
    void guardar(Admin admin);
    void modificar(Admin admin);
    Optional<Admin> buscarId(Integer id);
    void eliminar(Integer id);
    Optional<Admin> validarCredenciales(String nombre, String contrasena);
    List<Admin> obtenerTodosAdmins();
    List<Admin> obtenerTodosAdminsParaAdmin();
    Admin guardarAdmin(Admin admin);
    Admin crearAdmin(Admin admin);
    boolean activarAdmin(Integer id);
    boolean desactivarAdmin(Integer id);
    boolean existeAlMenosUnAdminActivo();
    boolean nombreDisponible(String nombre, Integer idExcluir);
    Optional<Admin> buscarPorNombre(String nombre);
}
