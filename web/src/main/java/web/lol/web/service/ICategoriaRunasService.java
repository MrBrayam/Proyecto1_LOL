package web.lol.web.service;

import java.util.List;
import java.util.Optional;

import web.lol.web.model.CategoriaRunas;

public interface ICategoriaRunasService {
    List<CategoriaRunas> buscarTodos();
    Optional<CategoriaRunas> buscarId(Integer id);
    Optional<CategoriaRunas> buscarPorNombre(String nombre);
}
