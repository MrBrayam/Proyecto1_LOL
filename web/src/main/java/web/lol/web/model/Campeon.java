package web.lol.web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "campeones")
public class Campeon {
    @Id
    @Column(name = "ID_Campeon", length = 4)
    private String idCampeon;

    @Column(name = "Nombre_Campeon", length = 50, nullable = false)
    private String nombreCampeon;

    @Column(name = "Descripcion_Campeon", length = 150)
    private String descripcionCampeon;

    @Column(name = "Estado")
    private Boolean estado;

    // Campos adicionales para la aplicación web (no mapeados a la BD)
    @Transient
    private String imagenPath;

    @Transient
    private String letra;

    // Constructores
    public Campeon() {}

    public Campeon(String idCampeon, String nombreCampeon, String descripcionCampeon, Boolean estado) {
        this.idCampeon = idCampeon;
        this.nombreCampeon = nombreCampeon;
        this.descripcionCampeon = descripcionCampeon;
        this.estado = estado;
    }

    public Campeon(String idCampeon, String nombreCampeon, String descripcionCampeon, Boolean estado, String imagenPath, String letra) {
        this.idCampeon = idCampeon;
        this.nombreCampeon = nombreCampeon;
        this.descripcionCampeon = descripcionCampeon;
        this.estado = estado;
        this.imagenPath = imagenPath;
        this.letra = letra;
    }

    // Getters y setters
    public String getIdCampeon() {
        return idCampeon;
    }

    public void setIdCampeon(String idCampeon) {
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    // Métodos de conveniencia para compatibilidad con el código existente
    public String getNombre() {
        return nombreCampeon;
    }

    public void setNombre(String nombre) {
        this.nombreCampeon = nombre;
    }

    public String getTitulo() {
        return descripcionCampeon;
    }

    public void setTitulo(String titulo) {
        this.descripcionCampeon = titulo;
    }
}