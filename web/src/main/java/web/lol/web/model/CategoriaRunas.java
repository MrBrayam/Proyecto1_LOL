package web.lol.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias_runas")
@SQLDelete(sql = "UPDATE categorias_runas SET estado=0 WHERE id_categoria=?")
@SQLRestriction("estado = 1")
@JsonPropertyOrder({
    "id_categoria",
    "nombre_categoria",
    "descripcion",
    "color",
    "imagen_principal",
    "estado"
})
public class CategoriaRunas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria;

    @Column(name = "nombre_categoria", nullable = false, length = 50)
    private String nombre_categoria;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 7)
    private String color;

    @Column(name = "imagen_principal", nullable = false, length = 255)
    private String imagen_principal;

    @Column
    private Integer estado = 1;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"categoria", "hibernateLazyInitializer", "handler"})
    private List<Runas> runas;

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagen_principal() {
        return imagen_principal;
    }

    public void setImagen_principal(String imagen_principal) {
        this.imagen_principal = imagen_principal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Runas> getRunas() {
        return runas;
    }

    public void setRunas(List<Runas> runas) {
        this.runas = runas;
    }

    @Override
    public String toString() {
        return "CategoriaRunas [id_categoria=" + id_categoria + ", nombre_categoria=" + nombre_categoria + "]";
    }
}
