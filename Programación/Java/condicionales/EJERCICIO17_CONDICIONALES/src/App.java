import java.util.Scanner;
import Calculo.Procesos;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, introduzca un número entero: ");;
        int num1 = scanner.nextInt();
        Procesos.ordenar(num1);
        scanner.close();
    }
}
