package Ejercicio59;

public class Ejercicio59 {
    public static void ejercicio59(){
        System.out.print("Por favor, introduzca la altura del árbol: ");
        int altura = Integer.parseInt(System.console().readLine());
        for(int j=0;j<=altura-3;j++){
            System.out.print(" ");           
        }
        System.out.print("*");
        System.out.println();
        for(int i=1; i<=altura-2; i++){
            for(int k=i;k<=altura-2;k++){
                System.out.print(" ");
            }
            if(i==1 || i==altura-2){
                if(i==1){
                    System.out.print("^");
                }else{
                    System.out.print("^");
                    for(int t=1;t<=(1+2*(i-2));t++){
                        System.out.print("^");
                    }
                    System.out.print("^");
                }
            }else{
                System.out.print("^");
                for(int t=1;t<=(1+2*(i-2));t++){
                    System.out.print(" ");
                }
                System.out.print("^");
            }
            System.out.println();
        }
        for(int j=0;j<=altura-3;j++){
            System.out.print(" ");         
        }
        System.out.print("Y");
    }
}
