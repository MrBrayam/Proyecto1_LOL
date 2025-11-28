package web.lol.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.lol.web.model.Campeon;
import java.util.List;

@Repository
public interface CampeonRepository extends JpaRepository<Campeon, String> {
    
    List<Campeon> findByEstadoTrueOrderByNombreCampeon();
    
    @Query("SELECT c FROM Campeon c WHERE c.estado = true AND UPPER(SUBSTRING(c.nombreCampeon, 1, 1)) = :letra ORDER BY c.nombreCampeon")
    List<Campeon> findByLetraAndEstadoTrue(String letra);
    
    @Query("SELECT DISTINCT UPPER(SUBSTRING(c.nombreCampeon, 1, 1)) FROM Campeon c WHERE c.estado = true ORDER BY UPPER(SUBSTRING(c.nombreCampeon, 1, 1))")
    List<String> findDistinctLetrasActivas();
    
    List<Campeon> findByNombreCampeonContainingIgnoreCaseAndEstadoTrue(String nombre);
}