package web.lol.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import web.lol.web.model.CategoriaRunas;
import web.lol.web.model.Runas;
import web.lol.web.service.ICategoriaRunasService;
import web.lol.web.service.IRunasService;

@Controller
@RequestMapping("/runas")
public class RunasController {

    @Autowired
    private ICategoriaRunasService categoriaService;

    @Autowired
    private IRunasService runasService;

    @GetMapping
    public String runas(Model model) {
        List<CategoriaRunas> categorias = categoriaService.buscarTodos();
        model.addAttribute("title", "Runas");
        model.addAttribute("description", "Descubre las diferentes categorías de runas y potencia a tus campeones");
        model.addAttribute("categorias", categorias);
        return "runas";
    }

    @GetMapping("/{categoria}")
    public String runasCategoria(@PathVariable String categoria, Model model) {
        Optional<CategoriaRunas> catOpt = categoriaService.buscarPorNombre(categoria);
        if (catOpt.isEmpty()) {
            return "redirect:/runas";
        }

        CategoriaRunas cat = catOpt.get();
        List<Runas> runas = runasService.buscarPorCategoria(cat.getId_categoria());

        List<Runas> runasClaves = runas.stream()
            .filter(r -> "principal".equals(r.getTipo_runa()))
            .toList();
        List<Runas> runasSegundaFila = runas.stream()
            .filter(r -> "sub_runa_1".equals(r.getTipo_runa()))
            .toList();
        List<Runas> runasTerceraFila = runas.stream()
            .filter(r -> "sub_runa_2".equals(r.getTipo_runa()))
            .toList();
        List<Runas> runasCuartaFila = runas.stream()
            .filter(r -> "sub_runa_3".equals(r.getTipo_runa()))
            .toList();

        model.addAttribute("categoria", cat.getNombre_categoria());
        model.addAttribute("descripcion", cat.getDescripcion());
        model.addAttribute("color", cat.getColor());
        model.addAttribute("imagenPrincipal", cat.getImagen_principal());
        model.addAttribute("runasClaves", runasClaves);
        model.addAttribute("runasSegundaFila", runasSegundaFila);
        model.addAttribute("runasTerceraFila", runasTerceraFila);
        model.addAttribute("runasCuartaFila", runasCuartaFila);

        return "runas-detalle";
    }
}
