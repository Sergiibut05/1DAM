package Ejercicio63;

public class Ejercicio63 {
    public static void ejercicio63(){
        System.out.printf("Introduzca la altura de la primera pirámide: ");
        int altura1=Integer.parseInt(System.console().readLine());
        System.out.printf("Introduzca la altura de la segunda pirámide: ");
        int altura2=Integer.parseInt(System.console().readLine());
        int alturareal=0;
        if(altura1>altura2){
            alturareal=altura1;
        }else{
            alturareal=altura2;
        }
        int contador1=0;
        int contador2=0;
        for(int i=alturareal;i>=1;i--){
            if(altura1>=i){
                espacios(i);
                contador1=altura1-i;
                for(int p=0;p<1+(2*(contador1));p++){
                    System.out.print("*");
                }
                espacios(i);
                System.out.print(" ");
            }else{
                espacios(alturareal);
                System.out.print("   ");
            }
            if(altura2>=i){
                espacios(i);
                contador2=altura2-i;
                for(int k=0;k<1+(2*(contador2));k++){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
    public static void espacios(int i){
        for (int f=1;f<=i;f++){
            System.out.print(" ");
        }
    }
}
