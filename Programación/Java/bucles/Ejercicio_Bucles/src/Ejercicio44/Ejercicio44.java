package Ejercicio44;

public class Ejercicio44 {
    public static void ejercicio44(){
        System.out.printf("Por favor, introduzca un número entero positivo: ");
        int num = Integer.parseInt(System.console().readLine());
        System.out.printf("Introduzca la posición donde quiere insertar: ");
        int posicion = Integer.parseInt(System.console().readLine());
        System.out.printf("Introduzca el dígito que quiere insertar: ");
        int digito = Integer.parseInt(System.console().readLine());
        int longitud = (int)Math.log10(num)+1;
        int num1=num/(int)Math.pow(10, (longitud-(posicion-1)));
        int num2=num%(int)Math.pow(10, (longitud-(posicion-1)));
        System.out.println("El número resultante es "+num1+digito+num2);
    }
}
