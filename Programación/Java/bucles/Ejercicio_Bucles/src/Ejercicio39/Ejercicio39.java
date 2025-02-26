package Ejercicio39;

public class Ejercicio39 {
    public static void ejercicio39(){
        System.out.printf("Por favor, introduzca un número entero positivo: ");
        int num=Integer.parseInt(System.console().readLine());
        System.out.print(num+"! = ");
        int num1=1;
        for (int i=num;i>0;i--){
            num1=num1*i;
        }
        System.out.print(num1);
    }
}
