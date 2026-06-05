package web.lol.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import web.lol.web.model.Runas;

public interface RunasRepository extends JpaRepository<Runas, Integer> {
    @Query("SELECT r FROM Runas r WHERE r.categoria.id_categoria = :idCategoria ORDER BY r.tipo_runa")
    List<Runas> findByCategoriaId(@Param("idCategoria") Integer idCategoria);

    @Query("SELECT r FROM Runas r LEFT JOIN FETCH r.categoria ORDER BY r.nombre_runa")
    List<Runas> findAllForAdmin();

    @Modifying
    @Transactional
    @Query(value = "UPDATE runas SET estado = 1 WHERE Id_runa = :id", nativeQuery = true)
    int activarRuna(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE runas SET estado = 0 WHERE Id_runa = :id", nativeQuery = true)
    int desactivarRuna(@Param("id") Integer id);
}
