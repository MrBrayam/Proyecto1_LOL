package web.lol.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    
    // Para admin: obtener todos los campeones sin filtro de estado
    @Query(value = "SELECT * FROM campeones ORDER BY Nombre_Campeon", nativeQuery = true)
    List<Campeon> findAllForAdmin();
    
    // Activar campeón usando consulta nativa para evitar el filtro @Where
    @Modifying
    @Transactional
    @Query(value = "UPDATE campeones SET Estado = 1 WHERE ID_Campeon = :id", nativeQuery = true)
    int activarCampeon(@Param("id") String id);
    
    // También agregar método para desactivar por consistencia
    @Modifying
    @Transactional
    @Query(value = "UPDATE campeones SET Estado = 0 WHERE ID_Campeon = :id", nativeQuery = true)
    int desactivarCampeon(@Param("id") String id);
}