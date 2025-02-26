package ejercicio16;

public class Linea {
    private Punto orig;
    private Punto dst;

    public Linea(Punto orig, Punto dst){
        this.orig = orig;
        this.dst = dst;
    }

    @Override
    public String toString() {
        return String.format(
            "Línea formada por los puntos %s y %s",
            this.orig, this.dst);
    }
    
}
