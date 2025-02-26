package Ejercicio13;

public class Ejercicio13 {
    public static void ejercicio13(){
        System.out.println("Por favor, introduzca 10 números enteros:");
        int n = 0;
        int positivo = 0;
        int negativo = 0;
        for (int i = 0; i<10;i++){
            n = Integer.parseInt(System.console().readLine());
            if (n>=0){
                positivo=positivo+1;
            }
            else{
                negativo=negativo+1;
            }
        }
        System.out.printf("Ha introducido %d positivos y %d negativos.",positivo,negativo);
    }
}
