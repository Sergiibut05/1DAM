package Ejercicio61;

public class Ejercicio61 {
    public static void ejercicio61(){
        System.out.print("Introduzca la altura de la V (un número mayor o igual a 3): ");
        int altura = Integer.parseInt(System.console().readLine());
        for(int i=1;i<=altura;i++){
            for(int j=i;j>1;j--){
                System.out.print(" ");
            }
            if(i==altura){
                System.out.print("******");
            }else{
                System.out.print("***");
                for(int k=1;k<=(2*(altura-i));k++){
                    System.out.print(" ");
                }
                System.out.print("***");
            }
            System.out.println();
        }
    }
}
