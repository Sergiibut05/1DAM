package Ejercicio43;

public class Ejercicio43 {
    public static void ejercicio43(){
        System.out.printf("Por favor, introduzca un número entero positivo: ");
        int num = Integer.parseInt(System.console().readLine());
        System.out.printf("Introduzca la posición a partir de la cual quiere partir el número: ");
        int partir=Integer.parseInt(System.console().readLine());
        int longitud = (int)Math.log10(num)+1;
        int num1=num/(int)Math.pow(10, (longitud-(partir-1)));
        int num2=num%(int)Math.pow(10, (longitud-(partir-1)));
        System.out.printf("Los números partidos son el %d y el %d.",num1,num2);
        
    }
}
