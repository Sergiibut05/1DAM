package Ejercicio57;

public class Ejercicio57 {
    public static void ejercicio57(){
        System.out.print("Introduzca la altura de la figura: ");
        int altura = Integer.parseInt(System.console().readLine());
        for(int i=altura;i>=1;i--){
            if(i==altura || i==1){
                if(i==1){
                    for(int k=i;k<altura;k++){
                        System.out.print(" ");
                    }
                }
                for(int j=1; j<=i;j++){
                    System.out.print("*");
                }
            }else{
                for(int k=i;k<altura;k++){
                    System.out.print(" ");
                }
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
