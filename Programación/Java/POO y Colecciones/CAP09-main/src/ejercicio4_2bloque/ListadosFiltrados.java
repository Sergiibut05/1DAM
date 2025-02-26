package ejercicio4_2bloque;

import ejercicio3_2bloque.Disco;

public interface ListadosFiltrados {
    public Disco[] consultarPorAutor(String autor);
    public Disco[] consultarPorTitulo(String titulo);
    public Disco[] consultarPorGenero(String genero);
    public Disco[] consultarPorDuracion(int minDuracion, int maxDuracion);
}
