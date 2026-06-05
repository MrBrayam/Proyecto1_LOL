package web.lol.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.lol.web.model.Campeon;
import web.lol.web.service.ICampeonService;

@Controller
@RequestMapping("/campeones")
public class CampeonController {

    @Autowired
    private ICampeonService campeonService;

    @GetMapping
    public String campeones(Model model) {
        Map<String, java.util.List<Campeon>> campeonesPorLetra = campeonService.obtenerCampeonesPorLetra();
        model.addAttribute("title", "Campeones");
        model.addAttribute("description", "Descubre todos los campeones disponibles en League of Legends");
        model.addAttribute("campeonesPorLetra", campeonesPorLetra);
        return "campeones";
    }
}
