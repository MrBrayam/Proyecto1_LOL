package web.lol.web.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import jakarta.persistence.*;

@Entity
@Table(name = "campeones")
@SQLDelete(sql = "UPDATE campeones SET Estado=0 WHERE ID_Campeon=?")
@Where(clause = "Estado=1")
public class Campeon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Campeon")
    private Integer idCampeon;

    @Column(name = "Nombre_Campeon", length = 50, nullable = false)
    private String nombreCampeon;

    @Column(name = "Descripcion_Campeon", length = 150)
    private String descripcionCampeon;

    @Column(name = "Estado")
    private Integer estado = 1;

    @Column(name = "rutaimg", length = 200)
    private String rutaimg;

    @Transient
    private String imagenPath;

    @Transient
    private String letra;

    public Campeon() {}

    public Campeon(String nombreCampeon, String descripcionCampeon) {
        this.nombreCampeon = nombreCampeon;
        this.descripcionCampeon = descripcionCampeon;
        this.estado = 1;
    }

    public Campeon(String nombreCampeon, String descripcionCampeon, String rutaimg, String imagenPath, String letra) {
        this.nombreCampeon = nombreCampeon;
        this.descripcionCampeon = descripcionCampeon;
        this.rutaimg = rutaimg;
        this.estado = 1;
        this.imagenPath = imagenPath;
        this.letra = letra;
    }

    public Integer getIdCampeon() {
        return idCampeon;
    }

    public void setIdCampeon(Integer idCampeon) {
        this.idCampeon = idCampeon;
    }

    public String getNombreCampeon() {
        return nombreCampeon;
    }

    public void setNombreCampeon(String nombreCampeon) {
        this.nombreCampeon = nombreCampeon;
    }

    public String getDescripcionCampeon() {
        return descripcionCampeon;
    }

    public void setDescripcionCampeon(String descripcionCampeon) {
        this.descripcionCampeon = descripcionCampeon;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getRutaimg() {
        return rutaimg;
    }

    public void setRutaimg(String rutaimg) {
        this.rutaimg = rutaimg;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}