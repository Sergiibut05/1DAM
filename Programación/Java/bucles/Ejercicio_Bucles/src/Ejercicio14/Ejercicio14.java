package Ejercicio14;

public class Ejercicio14 {
    public static void ejercicio14(){
        System.out.println("Cálculo de una potencia");
        System.out.printf("Introduzca la base: ");
        int b = Integer.parseInt(System.console().readLine());
        System.out.printf("Introduzca el exponente:");
        int e = Integer.parseInt(System.console().readLine());
        double resultado = resultado = Math.pow(b, e);
        System.out.printf(b+"^"+e+"= %.0f",resultado);
    }
}
