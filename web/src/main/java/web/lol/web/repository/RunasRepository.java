package web.lol.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.lol.web.model.Runas;

public interface RunasRepository extends JpaRepository<Runas, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
