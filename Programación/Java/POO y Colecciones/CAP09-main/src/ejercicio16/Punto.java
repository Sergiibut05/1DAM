package ejercicio16;

public class Punto {

    private double x;
    private double y;

    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return String.format(
            "(%.1f, %.1f)", 
            x, y);
    }
    
}
