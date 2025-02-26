package Ejercicio60;

public class Ejercicio60 {
    public static void ejercicio60(){
        System.out.print("Introduzca la altura de los calcetines: ");
        int altura = Integer.parseInt(System.console().readLine());
        for(int i=1; i<=altura-2;i++){
            System.out.println("***    ***");
        }
        for(int j=1;j<=2;j++){
            System.out.println("****** ******");
        }
    }
}
