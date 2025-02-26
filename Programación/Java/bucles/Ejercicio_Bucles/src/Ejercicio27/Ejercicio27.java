package Ejercicio27;

public class Ejercicio27 {
    public static void ejercicio27(){
        System.out.println("Introduzca un número entero mayor que 1: ");
        int maximo = Integer.parseInt(System.console().readLine());
        int suma =0;
        int contador =0;
        for (int i=3; i<=maximo;i++){
            if (i%3==0){
                System.out.print(i+" ");
                suma = suma +i;
                contador=contador+1;
            }
        }
        System.out.println();
        System.out.printf("Desde 1 hasta %d hay %d múltiplos de 3 y suman %d",maximo,contador,suma);
    }
}
