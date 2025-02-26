import java.util.Scanner;
import Dias.Condicionales;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Por favor, introduzca un día de la semana y le diré qué asignatura toca a primera hora ese día");
        String dia = scanner.nextLine().toLowerCase();
        Condicionales condicionales = new Condicionales(dia);
        String result = Condicionales.condicion(condicionales);
        System.out.println(result);
        scanner.close();
    }

}
/*private static String pedirpalabra(Scanner scanner, String message){
    String palabra = null;
    Boolean Numerovalido = false;
    while (!Numerovalido) {
        System.out.println(message);
        try {
            palabra = scanner.nextLine();
            if (palabra.equals("lunes") || palabra.equals("martes") || palabra.equals("miercoles") || palabra.equals("jueves") || palabra.equals("viernes")) {
                Numerovalido = true;
            }
            else {
                System.out.println("Error: Escriba un Dia de entre semana.");
            }
                palabra = scanner.nextLine();
                Numerovalido = true;
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
    return palabra;
}*/