package Ejercicio52;

public class Ejercicio52 {
    public static void ejercicio52(){
        System.out.println("Introduzca un número: ");
        int num = Integer.parseInt(System.console().readLine());
        int longitud = (int)Math.log10(num)+1;
        int num1=0;
        if (longitud>1){
            num1=(num%(int)Math.pow(10, longitud-1))*10+(num/(int)Math.pow(10, longitud-1));
            System.out.println("El número resultado es "+num1);
        }else{
            System.out.println("El número resultado es "+num);
        }
    }
}
