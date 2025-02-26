package Ejercicio12;
public class Ejercicio12{
    public static void ejercicio12(){
        System.out.println("Este programa muestra los n primeros números de la serie de Fibonacci.");
        System.out.printf("Por favor, introduzca n: ");
        int n = Integer.parseInt(System.console().readLine());
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 0; i<n; i++){
            System.out.println(a);
            c= a;
            a = a+b;
            b=c;
            
        }

    }
}