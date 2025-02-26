package Ejercicio64;

public class Ejercicio64 {
    public static void ejercicio64(){
        int altura=3;
        int anchura=6;
        rectangulo(altura, anchura);

    }
    public static void rectangulo(int altura, int anchura){
        for(int i=1;i<=altura;i++){
            if(i==1 || i==altura){
                for(int j=1;j<=anchura;j++){
                    System.out.print("*");
                }

            }else{
                System.out.print("*");
                for(int l=1;l<=anchura-2;l++){
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();
        }
        menu(altura, anchura);
    }
    public static void eleccion(int indicacion, int altura, int anchura){
        int cambiador=0;
        switch(indicacion){
            case 1:
                altura=altura+1;
                anchura=anchura+1;
                rectangulo(altura, anchura);
                break;
            case 2:
                if(altura==2 || anchura==2){
                    System.out.println("El mínimo es 2");
                }else{
                    altura=altura-1;
                anchura=anchura-1;
                rectangulo(altura, anchura);
                }
                break;
            case 3:
                cambiador=altura;
                altura=anchura;
                anchura=cambiador;
                rectangulo(altura, anchura);
                break;
            case 4:
                break;
            default:
                System.out.println("Elije bien HDP");
                break;
        }
    }
    public static void menu(int altura, int anchura){
        System.out.println("1. Agrandarlo");
        System.out.println("2. Achicarlo");
        System.out.println("3. Cambiar la orientación");
        System.out.println("4. Salir");
        System.out.print("Indique qué quiere hacer con el rectángulo: ");
        int indicacion= Integer.parseInt(System.console().readLine());
        eleccion(indicacion, altura, anchura);
    }
}
