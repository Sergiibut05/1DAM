package Ejercicio21;

public class Ejercicio21 {
    public static void ejercicio21(){
        System.out.println("Por favor, vaya introduciendo números enteros.%n Puede terminar mediante la introducción de un número negativo.");
            int n=0;
            int i=-1;
            int par=0;
            int impar=0;
            do{
                i=i+1;
                if ((n%2)==0){
                    if (n>par){
                        par=n;
                    }
                }
                else{
                    impar=impar+n;
                }
                n = Integer.parseInt(System.console().readLine());
            } while (n<0);
            System.out.println("La media de los impares es "+(impar/i));
            System.out.println("El máximo de los pares es "+par);        
    }
}
