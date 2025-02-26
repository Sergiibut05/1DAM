import java.util.Scanner;
import Calculo.Procesos;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Este programa ordena tres números introducidos por teclado.");
        System.out.println("Por favor, vaya introduciendo los tres números y pulsando INTRO:");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();
        Procesos.ordenar(num1, num2, num3);
        scanner.close();
    }
}
