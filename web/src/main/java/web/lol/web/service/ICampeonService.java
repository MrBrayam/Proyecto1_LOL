package web.lol.web.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.lol.web.model.Campeon;

public interface ICampeonService {
    List<Campeon> buscarTodos();
    void guardar(Campeon campeon);
    void modificar(Campeon campeon);
    Optional<Campeon> buscarId(Integer id);
    void eliminar(Integer id);
    List<Campeon> obtenerTodosCampeones();
    Map<String, List<Campeon>> obtenerCampeonesPorLetra();
    List<Campeon> buscarCampeonesPorNombre(String nombre);
    Page<Campeon> findAllForAdminPaginated(Pageable pageable);
    Page<Campeon> findAllForAdminPaginatedNative(Pageable pageable);
    List<Campeon> findAllForAdmin();
    void activarCampeon(Integer id);
    void desactivarCampeon(Integer id);
}
