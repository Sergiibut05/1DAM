package Ejercicio29;

public class Ejercicio29 {
    public static void ejercicio29(){
        System.out.printf("Introduzca un número entero positivo (relativamnte grande): ");
        int maximo = Integer.parseInt(System.console().readLine());
        System.out.println("Introduzca un numero(relativamente pequeño): ");
        int divisor=  Integer.parseInt(System.console().readLine());
        System.out.println("Los numeros enteros positivos menores que 200 que no son divisibles entre 3 son los siguientes: ");
        for (int i=1; i<maximo; i++){
            if(i%divisor !=0){
                System.out.print(i+" ");
            }
        }
    }
}
