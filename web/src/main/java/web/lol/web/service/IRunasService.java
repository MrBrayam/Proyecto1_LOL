package web.lol.web.service;

import java.util.List;
import java.util.Optional;

import web.lol.web.model.Runas;

public interface IRunasService {
    List<Runas> buscarTodos();
    void guardar(Runas runas);
    void modificar(Runas runas);
    Optional<Runas> buscarId(Integer id);
    void eliminar(Integer id);
}
