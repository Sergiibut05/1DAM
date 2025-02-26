package ej18;

public class Ej18 {
    public static void primeraParte(){
        System.err.println("---------------------------------------------------------------------");
        System.out.print("|Índice ");
        for(int i = 0; i <= 9; i++){
            System.out.printf("|   %d ", i);
        }
        System.out.println("|");
        System.out.print("|Valor  ");
    }

    public static int numAleatorio(){
        return (int)(Math.random() * 201);
    }

    public static void main(String[] args) {
        int[] numeros = new int[10];
        int[] numerosMayores = new int[10];
        int[] numerosMenores = new int[10];
        int contMayores = 0;
        int contMenores = 0;
        System.out.println("Array original:");
        primeraParte();
        for(int i = 0; i <= 9; i++){
            numeros[i] = numAleatorio();
            System.out.printf("| %3d ", numeros[i]);
        }
        for(int i = 0; i <= 9; i++){
            if (numeros[i] < 100) {
                numerosMenores[contMenores] = numeros[i];
                contMenores++;
            }else{
                numerosMayores[contMayores] = numeros[i];
                contMayores++;
            }
        } 
        System.out.println("|");
        System.err.println("---------------------------------------------------------------------");
        System.out.println("\nArray resultado:");
        primeraParte();
        for(int i = 0; i <= 9; i++){
            if (numerosMenores[i] != 0) {
                System.out.printf("| %3d ", numerosMenores[i]);
            }
            if (numerosMayores[i] != 0) {
            System.out.printf("| %3d ", numerosMayores[i]);
            }
        }
        System.out.println("|");
        System.err.println("---------------------------------------------------------------------");
    }
}