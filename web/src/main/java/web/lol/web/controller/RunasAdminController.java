package web.lol.web.controller;

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
import web.lol.web.model.Runas;
import web.lol.web.service.ICategoriaRunasService;
import web.lol.web.service.IRunasService;

@Controller
@RequestMapping("/admin/runas")
public class RunasAdminController {

    @Autowired
    private IRunasService runasService;

    @Autowired
    private ICategoriaRunasService categoriaService;

    private boolean verificarSesion(HttpSession session) {
        return session.getAttribute("adminLogueado") != null;
    }

    @GetMapping
    public String listarRunas(Model model, HttpSession session) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        model.addAttribute("runas", runasService.findAllForAdmin());
        return "admin/runas/index";
    }

    @GetMapping("/form")
    public String formularioNuevo(Model model, HttpSession session) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        model.addAttribute("runa", new Runas());
        model.addAttribute("categorias", categoriaService.buscarTodos());
        return "admin/runas/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        Optional<Runas> opt = runasService.buscarId(id);
        if (opt.isPresent()) {
            model.addAttribute("runa", opt.get());
            model.addAttribute("categorias", categoriaService.buscarTodos());
            return "admin/runas/form";
        }
        return "redirect:/admin/runas";
    }

    @PostMapping("/create")
    public String crear(@RequestParam("nombre_runa") String nombre,
                        @RequestParam("descripcion_runa") String descripcion,
                        @RequestParam("tipo_runa") String tipo,
                        @RequestParam("url_img") String urlImg,
                        @RequestParam("id_categoria") Integer idCategoria,
                        HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        Runas runa = new Runas();
        runa.setNombre_runa(nombre);
        runa.setDescripcion_runa(descripcion);
        runa.setTipo_runa(tipo);
        runa.setUrl_img(urlImg);
        runa.setEstado(1);
        categoriaService.buscarId(idCategoria).ifPresent(runa::setCategoria);
        runasService.guardar(runa);
        ra.addFlashAttribute("mensaje", "Runa creada exitosamente");
        return "redirect:/admin/runas";
    }

    @PostMapping("/update")
    public String actualizar(@RequestParam("Id_runa") Integer id,
                             @RequestParam("nombre_runa") String nombre,
                             @RequestParam("descripcion_runa") String descripcion,
                             @RequestParam("tipo_runa") String tipo,
                             @RequestParam("url_img") String urlImg,
                             @RequestParam("id_categoria") Integer idCategoria,
                             HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        Optional<Runas> opt = runasService.buscarId(id);
        if (opt.isPresent()) {
            Runas runa = opt.get();
            runa.setNombre_runa(nombre);
            runa.setDescripcion_runa(descripcion);
            runa.setTipo_runa(tipo);
            runa.setUrl_img(urlImg);
            categoriaService.buscarId(idCategoria).ifPresent(runa::setCategoria);
            runasService.modificar(runa);
            ra.addFlashAttribute("mensaje", "Runa actualizada exitosamente");
        }
        return "redirect:/admin/runas";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        runasService.desactivarRuna(id);
        ra.addFlashAttribute("mensaje", "Runa desactivada");
        return "redirect:/admin/runas";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (!verificarSesion(session)) return "redirect:/admin/login";
        runasService.activarRuna(id);
        ra.addFlashAttribute("mensaje", "Runa activada");
        return "redirect:/admin/runas";
    }
}
