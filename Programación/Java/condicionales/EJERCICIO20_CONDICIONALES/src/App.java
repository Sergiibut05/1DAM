import java.util.Scanner;
import Calculo.Procesos;
public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("A continuación deberá introducir una hora del día, primero introducirá la hora y luego los minutos.");
        System.out.println("Día de la semana: ");
        String c = scanner.nextLine();
        int d = Procesos.diasemana(c);
        int a = pedirnumero(scanner, "hora: ");
        int b = pedirnumero(scanner, "minuto: ");
        Procesos.tiempoviernes(a, b, d, c);
        scanner.close();
    }
    private static int pedirnumero(Scanner scanner, String message) {
        int numero = 0;
        Boolean Numerovalido = false;
        while (!Numerovalido) {
            System.out.println(message);
            try {
                numero = Integer.parseInt(scanner.nextLine());
                Numerovalido = true;
            } catch (NumberFormatException e){
                System.out.println("Error: No has ingresado un valor válido. Intenta de nuevo.");
            } catch (Exception e){
                System.out.println("Ha ocurrido un error");
            }
        }
        return numero;
    }
    
}
