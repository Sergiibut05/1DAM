import java.util.Scanner;

import Calculo.Procesos;

public class PrimeraCifra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Por favor, introduzca un número entero positivo (de 5 cifras como máximo): ");
        int numero = scanner.nextInt();
        Procesos.ordenar(numero);
        scanner.close();
    }
}