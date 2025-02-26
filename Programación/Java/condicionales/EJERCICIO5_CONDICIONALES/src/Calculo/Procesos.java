package Calculo;

public class Procesos {
    public static Double ecprimergrado(Double a, Double b){
        Double resultado = null;
        if (a != 0) {
            resultado = -b/a;
        }
        else {
            System.out.println("Esa ecuación no tiene solución real.");
        }
        return resultado;
    }
}
