package Calculo; 
public class Procesos {
    public static Double calculotiempo(Double a){
        Double resultado = null;
        Double numero = (2*a)/9.81;
        try {
            resultado = Math.sqrt(numero);
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
            return resultado;
    }
}
