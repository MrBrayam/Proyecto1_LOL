package web.lol.web.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lol.web.model.Runas;
import web.lol.web.repository.RunasRepository;
import web.lol.web.service.IRunasService;

@Service
public class RunasService implements IRunasService {

    @Autowired
    private RunasRepository repoRunas;

    public List<Runas> buscarTodos() {
        return repoRunas.findAll();
    }

    public void guardar(Runas runas) {
        repoRunas.save(runas);
    }

    public void modificar(Runas runas) {
        repoRunas.save(runas);
    }

    public Optional<Runas> buscarId(Integer id) {
        return repoRunas.findById(id);
    }

    public void eliminar(Integer id) {
        repoRunas.deleteById(id);
    }

    public List<Runas> buscarPorCategoria(Integer idCategoria) {
        return repoRunas.findByCategoriaId(idCategoria);
    }
}
