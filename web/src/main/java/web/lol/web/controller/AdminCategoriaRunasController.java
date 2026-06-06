package web.lol.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import web.lol.web.model.CategoriaRunas;
import web.lol.web.service.ICategoriaRunasService;

@Controller
@RequestMapping("/admin/categorias")
public class AdminCategoriaRunasController {

    @Autowired
    private ICategoriaRunasService categoriaService;

    private boolean verificarSesion(HttpSession session) {
        return session.getAttribute("adminLogueado") != null;
    }

    @GetMapping
    public String listarCategorias(Model model, HttpSession session) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        List<CategoriaRunas> categorias = categoriaService.findAllForAdmin();
        model.addAttribute("categorias", categorias);
        return "admin/categorias/index";
    }

    @GetMapping("/form")
    public String formularioNuevo(Model model, HttpSession session) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        model.addAttribute("categoria", new CategoriaRunas());
        return "admin/categorias/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        Optional<CategoriaRunas> opt = categoriaService.buscarId(id);
        if (opt.isPresent()) {
            model.addAttribute("categoria", opt.get());
            return "admin/categorias/form";
        }
        return "redirect:/admin/categorias";
    }

    @PostMapping("/create")
    public String crear(@RequestParam("nombre_categoria") String nombre,
                        @RequestParam("descripcion") String descripcion,
                        @RequestParam("color") String color,
                        @RequestParam("imagen_principal") String imagen,
                        HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        CategoriaRunas categoria = new CategoriaRunas();
        categoria.setNombre_categoria(nombre);
        categoria.setDescripcion(descripcion);
        categoria.setColor(color);
        categoria.setImagen_principal(imagen);
        categoria.setEstado(1);
        categoriaService.guardar(categoria);
        ra.addFlashAttribute("mensaje", "Categoría creada exitosamente");
        return "redirect:/admin/categorias";
    }

    @PostMapping("/update")
    public String actualizar(@RequestParam("id_categoria") Integer id,
                             @RequestParam("nombre_categoria") String nombre,
                             @RequestParam("descripcion") String descripcion,
                             @RequestParam("color") String color,
                             @RequestParam("imagen_principal") String imagen,
                             HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        Optional<CategoriaRunas> opt = categoriaService.buscarId(id);
        if (opt.isPresent()) {
            CategoriaRunas categoria = opt.get();
            categoria.setNombre_categoria(nombre);
            categoria.setDescripcion(descripcion);
            categoria.setColor(color);
            categoria.setImagen_principal(imagen);
            categoriaService.modificar(categoria);
            ra.addFlashAttribute("mensaje", "Categoría actualizada exitosamente");
        }
        return "redirect:/admin/categorias";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        categoriaService.desactivarCategoria(id);
        ra.addFlashAttribute("mensaje", "Categoría desactivada");
        return "redirect:/admin/categorias";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        categoriaService.activarCategoria(id);
        ra.addFlashAttribute("mensaje", "Categoría activada");
        return "redirect:/admin/categorias";
    }
}
