import java.util.Scanner;
import Semana.Dias;
public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int numero = pedirnumero(scanner, "Por favor, introduzca un número del 1 al 7: ");
        Dias.diasdelasemana(numero);
    }
    private static int pedirnumero(Scanner scanner, String message) {
        int numero = 0;
        Boolean Numerovalido = false;
        while (!Numerovalido) {
            System.out.println(message);
            try {
                numero = Integer.parseInt(scanner.nextLine());
                if (numero < 8 & numero > 0){
                    Numerovalido = true;
                }
                else {
                    System.out.println("Tiene que ser un numero del 1-7 ");
                }
                
            } catch (NumberFormatException e){
                System.out.println("Error: No has ingresado un valor válido. Intenta de nuevo.");
            } catch (Exception e){
                System.out.println("Ha ocurrido un error");
            }
        }
        return numero;
    }
}

