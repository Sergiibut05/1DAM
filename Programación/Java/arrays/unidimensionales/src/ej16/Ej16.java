package ej16;

public class Ej16 {
    public static int numAleatorio(){
        return (int)(Math.random() * 401);
    }

    public static void main(String[] args) {
        int[] numeros = new int[20];
        for(int i = 0; i <= 19; i++){
            numeros[i] = numAleatorio();
            System.out.print(numeros[i] + " ");
        }
        System.out.print("\n¿Qué números quiere resaltar? (1 - los múltiplos de 5, 2 - los múltiplos de 7): ");
        int opcion = Integer.parseInt(System.console().readLine());
        for (int i = 0; i <= 19; i++){
            if (opcion == 1 && numeros[i] % 5 == 0){
                System.out.printf("[%d] ", numeros[i]);
            }else if (opcion == 2 && numeros[i] % 7 == 0){
                System.out.printf("[%d] ", numeros[i]);
            }else{
                System.out.printf("%d ", numeros[i]);
            }
        }
    }
}
