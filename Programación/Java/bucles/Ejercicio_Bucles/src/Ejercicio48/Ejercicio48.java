package Ejercicio48;
import java.util.Scanner;
public class Ejercicio48 {
    public static void ejercicio48() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca un número entero: ");
        long num = Long.parseLong(scanner.nextLine());
        long num1 = num;
        boolean[] encontrados = new boolean[10];  // Array para almacenar dígitos encontrados

        System.out.print("Dígitos que aparecen en el número: ");
        while (num1 != 0) {
            int valor = (int) num1 % 10;
            if (!encontrados[valor]) { // Solo imprime si aún no se ha encontrado
                System.out.print(valor + " ");
                encontrados[valor] = true;
            }
            num1 /= 10; // Divide entre 10 para avanzar al siguiente dígito
        }

        System.out.println("\nDígitos que no aparecen: ");
        for (int i = 0; i < 10; i++) {
            if (!encontrados[i]) { // Imprime los dígitos no encontrados
                System.out.print(i + " ");
            }
        }
        scanner.close();
    }
}