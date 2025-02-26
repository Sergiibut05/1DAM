package Ejercicio28;

public class Ejercicio28 {
    public static void ejercicio28(){
    System.out.printf("Por favor, introduzca un número entero: ");
    int num = Integer.parseInt(System.console().readLine());
    long factorial=1;
    for (int i=num; i>=2;i--){
        factorial=factorial*i;
    }
    System.out.println(num+"! = "+factorial);
    }
}

