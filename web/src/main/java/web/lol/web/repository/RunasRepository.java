package web.lol.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.lol.web.model.Runas;

public interface RunasRepository extends JpaRepository<Runas, Integer> {
    List<Runas> findByCategoria_Id_categoriaOrderByTipo_runa(Integer idCategoria);
}
