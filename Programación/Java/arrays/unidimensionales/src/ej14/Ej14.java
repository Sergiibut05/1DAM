package ej14;

public class Ej14 {
    public static void main(String[] args) {
        String[] colores = new String[8];
        String[] otros = new String[8];
        int contColores = 0;
        int contOtros = 0;
        System.out.println("Introduzca 8 palabras (vaya pulsando [INTRO] entre unay otra).");
        String[] palabras = new String[8];
        for(int i = 0; i <= 7; i++){
            String palabra = System.console().readLine();
            palabras[i] = palabra;
            if (palabra.equals("verde") || palabra.equals("rojo") || palabra.equals("azul") || palabra.equals("amarillo") || palabra.equals("naranja") || palabra.equals("rosa") || palabra.equals("negro") || palabra.equals("blanco") || palabra.equals("morado")){
                colores[contColores] = palabra;
                contColores++;
            }else{
                otros[contOtros] = palabra;
                contOtros++;
            }
        }
        System.out.println("\nArray original:");
        for(int i = 0; i <= 7; i++){
            System.out.printf("|   %d    ", i);
        }
        System.out.println("|");
        for(int i = 0; i <= 7; i++){
            System.out.printf("|%-8s", palabras[i]);
        }
        System.out.println("|");
        System.out.println("\nArray resultado");
        for(int i = 0; i <= 7; i++){
            System.out.printf("|   %d    ", i);
        }
        System.out.println("|");
        for(int i = 0; i <= contColores-1; i++){
            System.out.printf("|%-8s", colores[i]);
        }
        for(int i = 0; i <= contOtros-1; i++){
            System.out.printf("|%-8s", otros[i]);
        }
        System.out.println("|");
    }
}