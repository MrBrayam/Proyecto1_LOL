package web.lol.web.model;

import org.hibernate.annotations.SQLDelete;
import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
@SQLDelete(sql = "UPDATE administradores SET Estado=0 WHERE Id_Admin=?")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Admin")
    private Integer idAdmin;

    @Column(name = "Nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name = "Contrasena", length = 25, nullable = false)
    private String contrasena;

    @Column(name = "Estado")
    private Integer estado = 1;

    public Admin() {}

    public Admin(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.estado = 1;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}