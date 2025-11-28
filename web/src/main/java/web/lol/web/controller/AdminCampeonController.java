package web.lol.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.lol.web.model.Campeon;
import web.lol.web.repository.CampeonRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/campeones")
public class AdminCampeonController {

    @Autowired
    private CampeonRepository campeonRepository;

    @GetMapping
    public String listarCampeones(Model model) {
        List<Campeon> campeones = campeonRepository.findAll();
        model.addAttribute("campeones", campeones);
        return "admin/campeones/index";
    }

    @GetMapping("/form")
    public String formularioNuevo(Model model) {
        model.addAttribute("campeon", new Campeon());
        return "admin/campeones/form";
    }

    @PostMapping("/create")
    public String crear(
            @RequestParam("idCampeon") String idCampeon,
            @RequestParam("nombreCampeon") String nombreCampeon,
            @RequestParam("descripcionCampeon") String descripcionCampeon
    ) {
        System.out.println("=== CREANDO NUEVO CAMPEÓN ===");
        System.out.println("ID: " + idCampeon);
        System.out.println("Nombre: " + nombreCampeon);
        System.out.println("Descripción: " + descripcionCampeon);

        try {
            Campeon campeon = new Campeon();
            campeon.setIdCampeon(idCampeon);
            campeon.setNombreCampeon(nombreCampeon);
            campeon.setDescripcionCampeon(descripcionCampeon);
            campeon.setEstado(1);

            campeonRepository.save(campeon);
            System.out.println("Campeón guardado exitosamente");
            
            return "redirect:/admin/campeones";

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/admin/campeones/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, Model model) {
        Optional<Campeon> campeonOpt = campeonRepository.findById(id);
        if (campeonOpt.isPresent()) {
            model.addAttribute("campeon", campeonOpt.get());
            return "admin/campeones/form";
        }
        return "redirect:/admin/campeones";
    }

    @PostMapping("/update")
    public String actualizar(
            @RequestParam("idCampeon") String idCampeon,
            @RequestParam("nombreCampeon") String nombreCampeon,
            @RequestParam("descripcionCampeon") String descripcionCampeon
    ) {
        try {
            Optional<Campeon> opt = campeonRepository.findById(idCampeon);
            if (opt.isPresent()) {
                Campeon campeon = opt.get();
                campeon.setNombreCampeon(nombreCampeon);
                campeon.setDescripcionCampeon(descripcionCampeon);
                
                campeonRepository.save(campeon);
                System.out.println("Campeón actualizado: " + nombreCampeon);
            }
            return "redirect:/admin/campeones";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/campeones";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        // Con @SQLDelete, esto ejecutará UPDATE en lugar de DELETE
        campeonRepository.deleteById(id);
        System.out.println("Campeón eliminado (borrado lógico): " + id);
        return "redirect:/admin/campeones";
    }
}