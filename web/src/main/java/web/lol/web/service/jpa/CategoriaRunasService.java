package web.lol.web.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lol.web.model.CategoriaRunas;
import web.lol.web.repository.CategoriaRunasRepository;
import web.lol.web.service.ICategoriaRunasService;

@Service
public class CategoriaRunasService implements ICategoriaRunasService {

    @Autowired
    private CategoriaRunasRepository repoCategoria;

    public List<CategoriaRunas> buscarTodos() {
        return repoCategoria.findAll();
    }

    public Optional<CategoriaRunas> buscarId(Integer id) {
        return repoCategoria.findById(id);
    }

    public void guardar(CategoriaRunas categoria) {
        repoCategoria.save(categoria);
    }

    public void modificar(CategoriaRunas categoria) {
        repoCategoria.save(categoria);
    }

    public void eliminar(Integer id) {
        repoCategoria.deleteById(id);
    }

    public Optional<CategoriaRunas> buscarPorNombre(String nombre) {
        return repoCategoria.findByNombreCategoriaIgnoreCase(nombre);
    }

    public List<CategoriaRunas> findAllForAdmin() {
        return repoCategoria.findAllForAdmin();
    }

    public void activarCategoria(Integer id) {
        repoCategoria.activarCategoria(id);
    }

    public void desactivarCategoria(Integer id) {
        repoCategoria.desactivarCategoria(id);
    }
}
