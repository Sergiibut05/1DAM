package ej15;

import java.util.Scanner;

public class Ej15 {
    public static void pintarTabla(int ocupacion[]){
        System.out.print("Mesa nº   ");
        for (int i = 1; i <= 10; i++){
            System.out.printf("| %2d ", i);
        }
        System.out.println("|");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Ocupación ");
        for (int i = 0; i < 10; i++){
            System.out.printf("| %2d ", ocupacion[i]);
        }
        System.out.println("|");
    }

    public static int numAleatorio(){
        return (int)(Math.random() * 5);
    }

    public static void main(String[] args) {
        int[] ocupacion = new int[10];
        for(int i = 0; i < 10; i++){
            ocupacion[i] = numAleatorio();
        }
        pintarTabla(ocupacion);
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n¿Cuántos son? (Introduzca -1 para salir del programa): ");
        int eleccion = Integer.parseInt(scanner.nextLine());
        while (eleccion != -1) {
            if (eleccion < 1 || eleccion > 4) {
                System.out.println("Introduzca un número del 1 al 4");
            } else {
                boolean sentado = false;
                for(int i = 0; i < 10; i++){
                    if (4 - ocupacion[i] >= eleccion) {
                        System.out.printf("Por favor, siéntese en la mesa %d%n", i + 1);
                        ocupacion[i] += eleccion;
                        pintarTabla(ocupacion);
                        sentado = true;
                        break;
                    }
                }
                if (!sentado) {
                    System.out.println("Lo siento, no hay mesas disponibles para ese número de personas.");
                }
            }
            System.out.print("\n¿Cuántos son? (Introduzca -1 para salir del programa): ");
            eleccion = Integer.parseInt(scanner.nextLine());
        }
        scanner.close();
    }
}
