package web.lol.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @GetMapping
    public String contacto(Model model) {
        model.addAttribute("title", "Contacto");
        model.addAttribute("description", "Ponte en contacto con nosotros");
        return "contacto";
    }
}
