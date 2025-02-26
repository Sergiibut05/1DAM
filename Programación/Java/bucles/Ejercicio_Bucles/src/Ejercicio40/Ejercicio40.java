package Ejercicio40;

public class Ejercicio40 {
    public static void ejercicio40(){
        System.out.printf("Por favor, introduzca la altura del rombo: ");
        int altura = Integer.parseInt(System.console().readLine());
        if (altura%2!=0 && altura>2){
            for(int i=1;i<altura+1;i++){
                if(i==1 || i==altura){
                    for(int j=1;j<=altura/2;j++){
                        System.out.print(" ");
                    }
                    System.out.print("*");
                }else{
                    if(i<=altura/2+1){
                        for(int k=i;k<=altura/2;k++){
                            System.out.print(" ");
                        }
                        System.out.print("*");
                        int variable=i-2;
                        for(int f=0;f<variable+i-1;f++){
                            System.out.print(" ");
                        }
                    }else{
                        for(int l=altura/2+1;l<i;l++){
                            System.out.print(" ");
                        }
                        System.out.print("*");
                        for (int m = 1; m < (altura - i) * 2; m++) {
                            System.out.print(" ");
                        }
                    }
                    System.out.print("*");
                }
                System.out.println();
            }
        }else{
            System.out.println("Datos incorrectos. Debe introducir una altura impar mayor o igual a 3.");
        }
    }
}
