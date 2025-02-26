package ejercicio9;

public class Movil extends Terminal{
    private double costeLlamadas;
    private String tarifa;
    public Movil(String numero, String tarifa){
        super(numero);
        this.tarifa = tarifa;
        this.costeLlamadas = 0;
    }

    private double costeTarifa(){
        return switch(tarifa){
            case "rata"->0.06;
            case "mono"->0.12;
            case "bisonte"->0.30;
            default->0;
        };
    }
    /**
     * llama
     * Gestiona la llamada de un terminal de manera que
     * se incremantan los tiempos en llamada de ambos terminales y
     * se factura al terminal que llama dependiendo de la tarifa contratada
     * @param t terminal que recibe la llamada
     * @param duracion duración de la llamada
     */
    @Override
    public void llama(Terminal t, int duracion){
        super.llama(t, duracion);
        // Calculo el coste de la llamada para el terminal que está llamando
        costeLlamadas += ((double)duracion/60)*costeTarifa();
    }


    
    @Override
    public String toString(){
        return String.format(
            "%s - tarificados %.2f euros", 
            super.toString(), 
            costeLlamadas);
    }

}
