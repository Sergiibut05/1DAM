package Ejercicio25;

import java.util.Scanner;

public class Ejercicio25 {
    public static void ejercicio25() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduzca un número entero: ");
        int n1 = Integer.parseInt(scanner.nextLine());  // Leemos el número entero
        System.out.printf("Si le damos la vuelta al %d tenemos el ", n1);

        // Extraemos y mostramos los dígitos de n1 en orden inverso
        while (n1 > 0) {
            int n2 = n1 % 10;  // Obtenemos el último dígito de n1
            System.out.printf("%d", n2);  // Imprimimos el dígito
            n1 = n1 / 10;  // Eliminamos el último dígito de n1
        }

        System.out.println();  // Salto de línea al final
    }
}
