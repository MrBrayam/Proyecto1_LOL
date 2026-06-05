package web.lol.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.lol.web.model.Runas;

public interface RunasRepository extends JpaRepository<Runas, Integer> {
    @Query("SELECT r FROM Runas r WHERE r.categoria.id_categoria = :idCategoria ORDER BY r.tipo_runa")
    List<Runas> findByCategoriaId(@Param("idCategoria") Integer idCategoria);
}
