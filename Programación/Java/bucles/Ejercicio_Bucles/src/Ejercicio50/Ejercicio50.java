package Ejercicio50;

public class Ejercicio50 {
    public static void ejercicio50(){
        System.out.print("Introduzca la altura (5 como mínimo): ");
        int altura = Integer.parseInt(System.console().readLine());
        System.out.println("Introduzca el número de espacios entre los números (1 como mínimo): ");
        int espacios = Integer.parseInt(System.console().readLine());
        for(int i=1; i<=altura;i++){
            if(i==1 || i==3 || i==altura){
                System.out.print("*");
                espacios(espacios);
                System.out.print("****");
                espacios(espacios);
                System.out.print("****");
            }else{
                if(i==2){
                    System.out.print("*");
                    espacios(espacios);
                    System.out.print("*   ");
                    espacios(espacios);
                    System.out.print("*");
                }else{
                    System.out.print("*");
                    espacios(espacios);
                    System.out.print("   ");
                    System.out.print("*");
                    espacios(espacios);
                    System.out.print("   ");
                    System.out.print("*");
                }
                
            }
            System.out.println();
        }

    }
    public static void espacios(int espacios){
        for(int e=1; e<=espacios; e++){
            System.out.print(" ");
        }
    }
}
