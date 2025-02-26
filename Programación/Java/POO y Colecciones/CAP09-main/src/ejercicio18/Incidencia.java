package ejercicio18;

public class Incidencia {
    private int cod;
    private int puesto;
    private String problema;
    private boolean resuelta;
    private String resolucion;
    private static int incidencias;
    private static int resueltas;

    public Incidencia(int puesto, String problema){
        this.puesto = puesto;
        this.problema = problema;
        this.cod = ++incidencias;
        this.resuelta = false;
        this.resolucion = "";
    }

    public void resuelve(String resolucion){
        this.resuelta = true;
        this.resolucion = resolucion;
        resueltas++;
    }

    public static int getPendientes(){
        return incidencias - resueltas;
    }

    @Override
    public String toString() {
        return String.format(
            "Incidencia %d - Puesto: %d - %s - %s %s",
            cod, puesto, problema, 
            resuelta?"Resuelta":"Pendiente",
            resuelta?"- "+resolucion:"");
    }

    
}
