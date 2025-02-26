package Ejercicio53;

public class Ejercicio53 {
    public static void ejercicio53(){
        System.out.print("Introduzca la altura de la figura: ");
        int altura = Integer.parseInt(System.console().readLine());
        for(int i=altura;i>=1;i--){
            for(int j=1; j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
