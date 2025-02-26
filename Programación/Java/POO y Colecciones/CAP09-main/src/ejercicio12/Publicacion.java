package ejercicio12;

public abstract class Publicacion {
    private String isbn;
    private int anio;
    private String titulo;

    protected Publicacion(String isbn, String titulo, int anio){
        this.isbn = isbn;
        this.anio = anio;
        this.titulo = titulo;
    }

    @Override
    public String toString(){
        return String.format(
            "ISBN: %s, Título: %s, Año de publicación: %d",
            isbn, titulo, anio);
    }
}
