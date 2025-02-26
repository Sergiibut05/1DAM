package ejercicio8;

/**
 * 
 */
public class Terminal implements TerminalInterface{
    private String numero;
    private int segundosEnLlamada;

    /**
     * Constructor con un parámetro
     * @param numero Número de teléfono del terminal
     */
    public Terminal(String numero){
        this.numero = numero;
        this.segundosEnLlamada = 0;
    }

    /** getNumero
     * 
     * @return devuelve el número de teléfono del terminal
     */
    public String getNumero() {
        return numero;
    }

    public int getSegundosEnLlamada() {
        return segundosEnLlamada;
    }

   
    @Override
    public String toString(){
        return String.format(
            "Nº %s - %ds de conversación",
            numero, segundosEnLlamada
        );
    }

    @Override
    public void llama(Terminal t, int duracion) {
        segundosEnLlamada+=duracion;
        t.segundosEnLlamada+=duracion;
    }
    

}
