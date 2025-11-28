package web.lol.web.model;

public class Campeon {
    private String nombre;
    private String titulo;
    private String imagenPath;
    private String letra;

    public Campeon() {}

    public Campeon(String nombre, String titulo, String imagenPath, String letra) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.imagenPath = imagenPath;
        this.letra = letra;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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