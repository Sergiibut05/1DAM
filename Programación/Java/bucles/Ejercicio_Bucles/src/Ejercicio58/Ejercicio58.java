package Ejercicio58;

public class Ejercicio58 {
    public static void ejercicio58(){
        System.out.print("Introduzca un número: ");
        int num = Integer.parseInt(System.console().readLine());
        System.out.print("La media de sus dígitos es ");
        int sumatorio=0;
        int contador=0;
        while (num!=0){
            sumatorio=sumatorio+num%10;
            contador=contador+1;
            num=num/10;
        }
        System.out.print((double)sumatorio/contador);
    }
}
