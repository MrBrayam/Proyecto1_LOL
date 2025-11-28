package web.lol.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import web.lol.web.service.CampeonService;

@Controller
public class WebController {

    @Autowired
    private CampeonService campeonService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Bienvenido a League of Legends");
        model.addAttribute("description", "Explora el mundo de League of Legends: conoce a los campeones, domina las runas y sumérgete en la experiencia definitiva de MOBA.");
        return "index";
    }

    @GetMapping("/campeones")
    public String campeones(Model model) {
        model.addAttribute("title", "Campeones de League of Legends");
        model.addAttribute("description", "Descubre todos los campeones disponibles en League of Legends");
        model.addAttribute("campeonesPorLetra", campeonService.obtenerCampeonesPorLetra());
        return "campeones";
    }

    @GetMapping("/runas")
    public String runas(Model model) {
        model.addAttribute("title", "Runas de League of Legends");
        model.addAttribute("description", "Conoce todas las runas disponibles para personalizar tu juego");
        return "runas";
    }

    @GetMapping("/runas/precision")
    public String runasPrecision(Model model) {
        model.addAttribute("title", "Runas de Precisión");
        model.addAttribute("description", "Runas enfocadas en ataques sostenidos y daño a distancia");
        return "precision";
    }

    @GetMapping("/runas/dominacion")
    public String runasDominacion(Model model) {
        model.addAttribute("title", "Runas de Dominación");
        model.addAttribute("description", "Runas enfocadas en explosiones de daño y caza");
        return "dominacion";
    }

    @GetMapping("/runas/brujeria")
    public String runasBrujeria(Model model) {
        model.addAttribute("title", "Runas de Brujería");
        model.addAttribute("description", "Runas enfocadas en habilidades y maná");
        return "brujeria";
    }

    @GetMapping("/runas/valor")
    public String runasValor(Model model) {
        model.addAttribute("title", "Runas de Valor");
        model.addAttribute("description", "Runas enfocadas en durabilidad y control de masas");
        return "valor";
    }

    @GetMapping("/runas/inspiracion")
    public String runasInspiracion(Model model) {
        model.addAttribute("title", "Runas de Inspiración");
        model.addAttribute("description", "Runas que rompen las reglas del juego");
        return "inspiracion";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("title", "Contacto");
        model.addAttribute("description", "Ponte en contacto con nosotros");
        return "contacto";
    }
}