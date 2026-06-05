package web.lol.web.service;

import java.util.List;
import java.util.Optional;

import web.lol.web.model.CategoriaRunas;

public interface ICategoriaRunasService {
    List<CategoriaRunas> buscarTodos();
    void guardar(CategoriaRunas categoria);
    void modificar(CategoriaRunas categoria);
    Optional<CategoriaRunas> buscarId(Integer id);
    void eliminar(Integer id);
    Optional<CategoriaRunas> buscarPorNombre(String nombre);
}
