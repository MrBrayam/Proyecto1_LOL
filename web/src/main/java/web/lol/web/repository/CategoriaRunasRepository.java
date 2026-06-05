package web.lol.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import web.lol.web.model.CategoriaRunas;

@Repository
public interface CategoriaRunasRepository extends JpaRepository<CategoriaRunas, Integer> {
    @Query("SELECT c FROM CategoriaRunas c WHERE LOWER(c.nombre_categoria) = LOWER(:nombre)")
    Optional<CategoriaRunas> findByNombreCategoriaIgnoreCase(@Param("nombre") String nombre);
}
