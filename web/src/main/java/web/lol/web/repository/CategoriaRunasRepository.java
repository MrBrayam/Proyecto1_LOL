package web.lol.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.lol.web.model.CategoriaRunas;

@Repository
public interface CategoriaRunasRepository extends JpaRepository<CategoriaRunas, Integer> {
    Optional<CategoriaRunas> findByNombre_categoriaIgnoreCase(String nombreCategoria);
}
