package Ejercicio24;

public class Ejercicio24 {
    public static void ejercicio24(){
        System.out.println("Este programa pinta una pirámide hecha a base de números.");
        System.out.println("Por favor, introduzca la altura de la pirámide: ");
        int n=Integer.parseInt(System.console().readLine());
        for (int i=1; i<=n;i++){
            for (int j=1;j<=n-i;j++){
                System.out.print("   ");
            }
            for (int g=1; g<=i;g++){
                System.out.printf("%2d",g);
                System.out.print(" ");
            }
            for (int f=i-1; f>0;f--){
                System.out.printf("%2d",f);
                System.out.print(" ");
            }
            System.out.println();
        }
    
    }
}