package web.lol.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.lol.web.model.CategoriaRunas;
import web.lol.web.service.ICategoriaRunasService;

@RestController
@RequestMapping("/api")
public class CategoriaRunasController {

    @Autowired
    private ICategoriaRunasService serviceCategoria;

    @GetMapping("/categorias_runas")
    public List<CategoriaRunas> buscarTodos() {
        return serviceCategoria.buscarTodos();  
    }

    @PostMapping("/categorias_runas")
    public CategoriaRunas guardar(@RequestBody CategoriaRunas registro) {
        serviceCategoria.guardar(registro);
        return registro;
    }

    @PutMapping("/categorias_runas")
    public CategoriaRunas modificar(@RequestBody CategoriaRunas registro) {
        serviceCategoria.modificar(registro);
        return registro;
    }

    @GetMapping("/categorias_runas/{id}")
    public Optional<CategoriaRunas> buscarId(@PathVariable("id") Integer id) {
        return serviceCategoria.buscarId(id);
    }

    @DeleteMapping("/categorias_runas/{id}")
    public String eliminar(@PathVariable Integer id) {
        serviceCategoria.eliminar(id);
        return "Registro Eliminado";
    }
}
