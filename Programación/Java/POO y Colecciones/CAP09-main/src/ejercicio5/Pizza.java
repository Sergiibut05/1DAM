package ejercicio5;

public class Pizza {
    private String tipo;
    private String tamanio;
    private boolean servida;
    private static int totalPedidas;
    private static int totalServidas;

    public Pizza(String tipo, String tamanio){
        this.tipo = tipo;
        this.tamanio = tamanio;
        totalPedidas++;
    }

    public String getTamanio() {
        return tamanio;
    }

    public String getTipo() {
        return tipo;
    }

    public static int getTotalPedidas(){
        return totalPedidas;
    }

    public static int getTotalServidas(){
        return totalServidas;
    }

    public void sirve(){
        if(!this.servida){
            this.servida = true;
            totalServidas++;
        } 
        else
            System.out.println("esa pizza ya se ha servido");
    }

    @Override
    public String toString(){
        return String.format("pizza %s %s, %s", this.tipo, this.tamanio, servida?"servida":"pedida");
    }
    
}
