package web.lol.web.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import web.lol.web.model.Campeon;
import web.lol.web.repository.CampeonRepository;
import web.lol.web.service.ICampeonService;

@Service
public class CampeonService implements ICampeonService {

    @Autowired
    private CampeonRepository campeonRepository;

    public List<Campeon> buscarTodos() {
        return campeonRepository.findAll();
    }

    public void guardar(Campeon campeon) {
        campeonRepository.save(campeon);
    }

    public void modificar(Campeon campeon) {
        campeonRepository.save(campeon);
    }

    public Optional<Campeon> buscarId(Integer id) {
        return campeonRepository.findById(id);
    }

    public void eliminar(Integer id) {
        campeonRepository.deleteById(id);
    }

    public List<Campeon> obtenerTodosCampeones() {
        try {
            List<Campeon> campeones = campeonRepository.findAllByOrderByNombreCampeon();
            System.out.println("Campeones obtenidos de la BD: " + campeones.size());

            campeones.forEach(this::asignarDatosAdicionales);
            return campeones;
        } catch (Exception e) {
            System.err.println("Error al obtener campeones: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Map<String, List<Campeon>> obtenerCampeonesPorLetra() {
        try {
            List<Campeon> campeones = obtenerTodosCampeones();
            System.out.println("Procesando " + campeones.size() + " campeones para agrupar por letra");

            Map<String, List<Campeon>> resultado = campeones.stream()
                    .collect(Collectors.groupingBy(
                        campeon -> campeon.getNombreCampeon().substring(0, 1).toUpperCase(),
                        TreeMap::new,
                        Collectors.toList()
                    ));

            System.out.println("Grupos creados: " + resultado.keySet());
            return resultado;
        } catch (Exception e) {
            System.err.println("Error al agrupar campeones por letra: " + e.getMessage());
            e.printStackTrace();
            return new TreeMap<>();
        }
    }

    public List<Campeon> buscarCampeonesPorNombre(String nombre) {
        List<Campeon> campeones = campeonRepository.findByNombreCampeonContainingIgnoreCase(nombre);
        campeones.forEach(this::asignarDatosAdicionales);
        return campeones;
    }

    private void asignarDatosAdicionales(Campeon campeon) {
        String nombre = campeon.getNombreCampeon();
        campeon.setLetra(nombre.substring(0, 1).toUpperCase());

        if (campeon.getRutaimg() != null && !campeon.getRutaimg().trim().isEmpty()) {
            campeon.setImagenPath(campeon.getRutaimg());
        } else {
            String nombreCarpeta = limpiarNombreParaCarpeta(nombre);
            String nombreArchivo = limpiarNombreParaArchivo(nombre);
            campeon.setImagenPath("/img/" + nombreCarpeta + "/Original" + nombreArchivo + ".jpg");
        }
    }

    private String limpiarNombreParaCarpeta(String nombre) {
        return nombre.replace(" ", "_");
    }

    private String limpiarNombreParaArchivo(String nombre) {
        switch (nombre) {
            case "Aurelion Sol":
                return "Aurelion Sol";
            case "Dr. Mundo":
                return "Dr.Mundo";
            case "Nunu y Willump":
                return "Nunu&Willump";
            default:
                return nombre;
        }
    }

    public Page<Campeon> findAllForAdminPaginated(Pageable pageable) {
        return campeonRepository.findAllForAdminPaginated(pageable);
    }

    public Page<Campeon> findAllForAdminPaginatedNative(Pageable pageable) {
        return campeonRepository.findAllForAdminPaginatedNative(pageable);
    }

    public List<Campeon> findAllForAdmin() {
        return campeonRepository.findAllForAdmin();
    }

    public void activarCampeon(Integer id) {
        campeonRepository.activarCampeon(id);
    }

    public void desactivarCampeon(Integer id) {
        campeonRepository.desactivarCampeon(id);
    }

    @PostConstruct
    public void inicializarDatos() {
        long count = campeonRepository.count();
        System.out.println("Campeones en la base de datos: " + count);
    }
}
