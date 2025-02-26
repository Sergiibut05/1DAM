/*package ej12;

public class Ej12 {
    public static void pintarArray(int numeros[]){
        System.out.println("Array original:");
        System.out.print("\n" + "| Índice ");
        for (int i = 0; i <= 9; i++){
            System.out.printf("|   %d ", i);
        }
        System.out.println("|");
        System.out.println("----------------------------------------------------------------------");
        System.out.print("| Valor  ");
        for (int i = 0; i <= 9; i++){
            System.out.printf("| %3d ", numeros[i]);
        }
        System.out.println("|");
    }
    public static int numAleatorio(){
        return (int)(Math.random() * 101);
    }
    public static void main(String[] args) {
        int[] numeros = new int[10];
        for(int i = 0; i <= 9; i++){
            numeros[i] = numAleatorio();
        }
        pintarArray(numeros);
        System.out.print("Introduzca la posición inicial (0-9): ");
        int posInicial = Integer.parseInt(System.console().readLine());
        System.out.print("Introduzca la posición final (0-9): ");
        int posFinal = Integer.parseInt(System.console().readLine());
        for(int i = posFinal-1; i <= 10; i++){

        }
    }
}*/