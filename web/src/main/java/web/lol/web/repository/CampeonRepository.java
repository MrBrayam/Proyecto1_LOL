package web.lol.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.lol.web.model.Campeon;
import java.util.List;

@Repository
public interface CampeonRepository extends JpaRepository<Campeon, Integer> {
    
    List<Campeon> findAllByOrderByNombreCampeon();
    
    @Query("SELECT c FROM Campeon c WHERE UPPER(SUBSTRING(c.nombreCampeon, 1, 1)) = :letra ORDER BY c.nombreCampeon")
    List<Campeon> findByLetra(String letra);
    
    @Query("SELECT DISTINCT UPPER(SUBSTRING(c.nombreCampeon, 1, 1)) FROM Campeon c ORDER BY UPPER(SUBSTRING(c.nombreCampeon, 1, 1))")
    List<String> findDistinctLetras();
    
    List<Campeon> findByNombreCampeonContainingIgnoreCase(String nombre);
    
    @Query(value = "SELECT * FROM campeones ORDER BY Nombre_Campeon", nativeQuery = true)
    List<Campeon> findAllForAdmin();
    
    @Query(value = "SELECT * FROM campeones ORDER BY Nombre_Campeon", 
           countQuery = "SELECT count(*) FROM campeones",
           nativeQuery = true)
    Page<Campeon> findAllForAdminPaginatedNative(Pageable pageable);
    
    @Query("SELECT c FROM Campeon c WHERE c.estado IN (0, 1) ORDER BY c.nombreCampeon")
    Page<Campeon> findAllForAdminPaginated(Pageable pageable);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE campeones SET Estado = 1 WHERE ID_Campeon = :id", nativeQuery = true)
    int activarCampeon(@Param("id") Integer id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE campeones SET Estado = 0 WHERE ID_Campeon = :id", nativeQuery = true)
    int desactivarCampeon(@Param("id") Integer id);
}