package ejercicio3_2bloque;

public class Disco {
    private final int LONGITUD_CODIGO = 6;
    private String cod;
    private String autor;
    private String titulo;
    private String genero;
    private int duracion;

    private static String nuevoCodigo(int longitud){
        
        String codigo = "";
        char minLetra = 'A';
        char maxLetra = 'Z';
        char minNumero = '0';
        char maxNumero = '9';
        for(int i = 1; i<=longitud; i++){
            if(Math.random()>0.5)
                codigo+=(char)(Math.random()*(maxLetra - minLetra - 1) + minLetra);
            else
                codigo+=(char)(Math.random()*(maxNumero - minNumero - 1) + minNumero);
        }
        return codigo;
    }
    public Disco(String autor, String titulo, String genero, int duracion){
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.cod = nuevoCodigo(LONGITUD_CODIGO);
    }


    public String getCod() {
        return cod;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }
    
    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return String.format(
            """
            ------------------------------------------
            Código: %s
            Autor: %s
            Título: %s
            Género: %s
            Duracion: %d
            ------------------------------------------
            """, cod, autor, titulo, genero, duracion);
    }
    
}
