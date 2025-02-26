import java.util.Scanner;
import Calculo.Procesos;
public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Calculo del tiempo de caida de un objeto.");
        Double a = pedirnumero(scanner, "Por favor, introduzca la altura (en metros) desde la que cae el objeto: ");
        Double resultado = Procesos.calculotiempo(a);
        System.out.println("El objeto tarda "+resultado+"segundos en caer.");
    }
    private static Double pedirnumero(Scanner scanner, String message) {
        Double numero = null;
        Boolean Numerovalido = false;
        while (!Numerovalido) {
            System.out.println(message);
            try {
                numero = Double.parseDouble(scanner.nextLine());
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
