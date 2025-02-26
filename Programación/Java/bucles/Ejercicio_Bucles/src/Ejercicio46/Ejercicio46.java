package Ejercicio46;

public class Ejercicio46 {
    public static void ejercicio46(){
        System.out.print("Por favor, introduzca la anchura del rectángulo (como mínimo 2): ");
        int anchura = Integer.parseInt(System.console().readLine());
        System.out.print("Ahora introduzca la altura (como mínimo 2): ");
        int altura = Integer.parseInt(System.console().readLine());
        if(anchura>1 && altura>1){
            for(int i=1; i<=altura;i++){
                if(i==1 || i==altura){
                    for(int j=1; j<=anchura;j++){
                        System.out.print("* ");
                    }
                }else{
                    System.out.print("* ");
                    for(int k=1; k<=anchura-2;k++){
                        System.out.print("  ");
                    }
                    System.out.print("* ");
                }
                System.out.println();
            }
        }else{
            System.out.println("Lo siento, los datos introducidos no son correctos, el valor mínimo para la anchura y la altura es 2.");
        }
    }
}
