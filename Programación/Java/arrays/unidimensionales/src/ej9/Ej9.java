package ej9;

public class Ej9 {
    public static void main(String[] args) {
        int numerosTotales = 8;
        System.out.println("Introduzca 8 números enteros, pulse INTRO despés de cada número: ");
        int[] numeros = new int[numerosTotales];
        for(int i = 0; i <= numerosTotales-1; i++){
            numeros[i] = Integer.parseInt(System.console().readLine());
        }
        System.out.println();
        for (int i = 0; i <= numerosTotales-1; i++){
            if (numeros[i]%2 == 0) {
                System.out.printf("%d par%n", numeros[i]);
            }else{
                System.out.printf("%d impar%n", numeros[i]);
            }
        }
    }
}
