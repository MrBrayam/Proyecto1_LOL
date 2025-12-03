package web.lol.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.lol.web.model.Admin;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
    // Buscar admin por nombre para login (solo activos)
    Optional<Admin> findByNombreAndEstado(String nombre, Integer estado);
    
    // Buscar admin por nombre para validación
    Optional<Admin> findByNombre(String nombre);
    
    // Obtener todos los administradores activos ordenados por nombre
    List<Admin> findAllByOrderByNombre();
    
    // Para admin: obtener todos los administradores sin filtro de estado
    @Query(value = "SELECT * FROM administradores ORDER BY Nombre", nativeQuery = true)
    List<Admin> findAllForAdmin();
    
    // Activar administrador
    @Modifying
    @Transactional
    @Query(value = "UPDATE administradores SET Estado = 1 WHERE Id_Admin = :id", nativeQuery = true)
    int activarAdmin(@Param("id") Integer id);
    
    // Desactivar administrador
    @Modifying
    @Transactional
    @Query(value = "UPDATE administradores SET Estado = 0 WHERE Id_Admin = :id", nativeQuery = true)
    int desactivarAdmin(@Param("id") Integer id);
    
    // Validar credenciales para login usando consulta nativa
    @Query(value = "SELECT * FROM administradores WHERE Nombre = :nombre AND Contrasena = :contrasena AND Estado = 1", nativeQuery = true)
    Optional<Admin> validarCredenciales(@Param("nombre") String nombre, @Param("contrasena") String contrasena);
    
    // Buscar admin por nombre usando consulta nativa
    @Query(value = "SELECT * FROM administradores WHERE Nombre = :nombre", nativeQuery = true)
    Optional<Admin> findByNombreNative(@Param("nombre") String nombre);
    
    // Buscar admin por nombre para login (solo activos) usando consulta nativa
    @Query(value = "SELECT * FROM administradores WHERE Nombre = :nombre AND Estado = :estado", nativeQuery = true)
    Optional<Admin> findByNombreAndEstadoNative(@Param("nombre") String nombre, @Param("estado") Integer estado);
}