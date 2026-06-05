package web.lol.web.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "runas")

@SQLDelete(sql = "UPDATE runas SET estado=0 WHERE id=?")
@SQLRestriction("estado = 1")

@JsonPropertyOrder({
    "Id_runa","nombre_runa","descripcion_runa","tipo_runa","url_img","estado"})

public class Runas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer Id_runa;
    private String nombre_runa;
    private String descripcion_runa;
    private String tipo_runa;
    private String url_img;
    private Integer estado = 1;
    
    public Integer getId_runa() {
        return Id_runa;
    }
    public void setId_runa(Integer id_runa) {
        Id_runa = id_runa;
    }
    public String getNombre_runa() {
        return nombre_runa;
    }
    public void setNombre_runa(String nombre_runa) {
        this.nombre_runa = nombre_runa;
    }
    public String getDescripcion_runa() {
        return descripcion_runa;
    }
    public void setDescripcion_runa(String descripcion_runa) {
        this.descripcion_runa = descripcion_runa;
    }
    public String getTipo_runa() {
        return tipo_runa;
    }
    public void setTipo_runa(String tipo_runa) {
        this.tipo_runa = tipo_runa;
    }
    public String getUrl_img() {
        return url_img;
    }
    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Runas [Id_runa=" + Id_runa + ", nombre_runa=" + nombre_runa + ", descripcion_runa=" + descripcion_runa
                + ", tipo_runa=" + tipo_runa + ", url_img=" + url_img + ", estado=" + estado + "]";
    }

}
