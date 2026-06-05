package web.lol.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.lol.web.model.CategoriaRunas;

@Repository
public interface CategoriaRunasRepository extends JpaRepository<CategoriaRunas, Integer> {
    @Query("SELECT c FROM CategoriaRunas c WHERE LOWER(c.nombre_categoria) = LOWER(:nombre)")
    Optional<CategoriaRunas> findByNombreCategoriaIgnoreCase(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM categorias_runas ORDER BY nombre_categoria", nativeQuery = true)
    List<CategoriaRunas> findAllForAdmin();

    @Modifying
    @Transactional
    @Query(value = "UPDATE categorias_runas SET estado = 1 WHERE id_categoria = :id", nativeQuery = true)
    int activarCategoria(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categorias_runas SET estado = 0 WHERE id_categoria = :id", nativeQuery = true)
    int desactivarCategoria(@Param("id") Integer id);
}
