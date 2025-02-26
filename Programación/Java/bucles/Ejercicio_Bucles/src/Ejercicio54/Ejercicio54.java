package Ejercicio54;

public class Ejercicio54 {
    public static void ejercicio54(){
        System.out.print("Introduzca la altura de la figura: ");
        int altura = Integer.parseInt(System.console().readLine());
        for(int i=altura;i>=1;i--){
            if(i==altura || i==1){
                for(int j=1; j<=i;j++){
                    System.out.print("*");
                }
            }else{
                System.out.print("*");
                for(int j=3; j<=i;j++){
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            
            System.out.println();
        }
    }
}
