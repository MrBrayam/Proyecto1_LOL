package web.lol.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.lol.web.model.Campeon;
import java.util.List;

@Repository
public interface CampeonRepository extends JpaRepository<Campeon, String> {
    
    // Con @Where, automáticamente filtra por estado=1
    List<Campeon> findAllByOrderByNombreCampeon();
    
    @Query("SELECT c FROM Campeon c WHERE UPPER(SUBSTRING(c.nombreCampeon, 1, 1)) = :letra ORDER BY c.nombreCampeon")
    List<Campeon> findByLetra(String letra);
    
    @Query("SELECT DISTINCT UPPER(SUBSTRING(c.nombreCampeon, 1, 1)) FROM Campeon c ORDER BY UPPER(SUBSTRING(c.nombreCampeon, 1, 1))")
    List<String> findDistinctLetras();
    
    List<Campeon> findByNombreCampeonContainingIgnoreCase(String nombre);
}