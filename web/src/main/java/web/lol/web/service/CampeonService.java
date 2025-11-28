package web.lol.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.lol.web.model.Campeon;
import web.lol.web.repository.CampeonRepository;
import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampeonService {
    
    @Autowired
    private CampeonRepository campeonRepository;
    
    public List<Campeon> obtenerTodosCampeones() {
        List<Campeon> campeones = campeonRepository.findByEstadoTrueOrderByNombreCampeon();
        // Asignar rutas de imagen y letra basado en el nombre
        campeones.forEach(this::asignarDatosAdicionales);
        return campeones;
    }
    
    public Map<String, List<Campeon>> obtenerCampeonesPorLetra() {
        List<Campeon> campeones = obtenerTodosCampeones();
        return campeones.stream()
                .collect(Collectors.groupingBy(
                    campeon -> campeon.getNombreCampeon().substring(0, 1).toUpperCase(),
                    TreeMap::new, 
                    Collectors.toList()
                ));
    }
    
    public List<Campeon> buscarCampeonesPorNombre(String nombre) {
        List<Campeon> campeones = campeonRepository.findByNombreCampeonContainingIgnoreCaseAndEstadoTrue(nombre);
        campeones.forEach(this::asignarDatosAdicionales);
        return campeones;
    }
    
    private void asignarDatosAdicionales(Campeon campeon) {
        String nombre = campeon.getNombreCampeon();
        // Asignar letra
        campeon.setLetra(nombre.substring(0, 1).toUpperCase());
        
        // Asignar ruta de imagen basada en el nombre del campeón
        String nombreLimpio = limpiarNombreParaRuta(nombre);
        campeon.setImagenPath("/img/" + nombreLimpio + "/Original" + nombreLimpio + ".jpg");
    }
    
    private String limpiarNombreParaRuta(String nombre) {
        // Reemplazar caracteres especiales y espacios
        return nombre.replace("'", "")
                    .replace(".", "")
                    .replace(" ", "_")
                    .replace("'", "");
    }
    
    @PostConstruct
    public void inicializarDatos() {
        // Solo verificar si hay datos, no insertar automáticamente
        // Los datos se manejarán desde la base de datos existente
        long count = campeonRepository.count();
        System.out.println("Campeones en la base de datos: " + count);
    }
}