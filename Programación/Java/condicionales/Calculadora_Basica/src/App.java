import java.util.Scanner;
import Calculadoras.Calculadora;
public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void menu() {
        System.out.println("***********************");
        System.out.println("1. Elegir Valores");
        System.out.println("2. Elegir Operador");
        System.out.println("3. Calcular");
        System.out.println("4. Salir");
        System.out.println("Elige Opcion: ");
    }
    public static int leeropcion(int ret){
        ret = Integer.parseInt(scanner.nextLine());
        return ret;
    }
    public static Calculadora obtenerValores(){
        double operando1 = pedirnumero(scanner, "Introduce el primer Valor: ");
        double operando2 = pedirnumero(scanner, "Introduce el segundo Valor");
        String Operador = null;
        Calculadora calculadora1 = new Calculadora(operando1, operando2, Operador, 0);
        return calculadora1;
        
    }

    public static String obtenerOperador(){
        String Operador = pedirOperador(scanner, "Elige el Operador Deseado: \n+\n-\n*\n/\nElige: ");
        return Operador;
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
    private static String pedirOperador(Scanner scanner, String message) {
        String palabra = null;
        Boolean Numerovalido = false;
        while (!Numerovalido) {
            System.out.println(message);
            try {
                palabra = scanner.nextLine();
                if (palabra.equals("+") || palabra.equals("-") || palabra.equals("*") || palabra.equals("/")){
                    Numerovalido = true;
                }
                else{
                    System.out.println("Elige uno de los Operadores Dados");
                }
                
            } catch (Exception e){
                System.out.println("Ha ocurrido un error");
            }
        }
        return palabra;
    }
    private static String pedirpalabra(Scanner scanner, String message) {
        String palabra = null;
        Boolean Numerovalido = false;
        while (!Numerovalido) {
            System.out.println(message);
            try {
                palabra = scanner.nextLine();
                Numerovalido = true;
            } catch (Exception e){
                System.out.println("Ha ocurrido un error");
            }
        }
        return palabra;
    }
    public static void main(String[] args) throws Exception {
        Calculadora calculadora1 = null;
        String Operador = null;
        int opcion = 0;
        String input = null;
        Double resultado = null;
        int opcion2 = 0;
        while(opcion != 4){
            opcion = 0;
            menu();
            try {
                opcion = leeropcion(0);
            } catch (NumberFormatException e) {
                System.out.println("Introduzca una opción correcta");
            } catch (Exception e){
                System.out.println("Ocurrió un error inesperado");
            }
            switch(opcion){
                case 1:
                    try {
                        calculadora1 = obtenerValores();    
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error al elegir valores");
                    }
                    break;
                case 2:
                    if( calculadora1!= null){
                        try {
                            Operador = obtenerOperador();
                            calculadora1.setOperador(Operador);
                        } catch (Exception e) {
                            System.out.println("Ocurrió un error elegir el Operador");
                        }
                    }
                    else{
                        System.out.println("Debe añadir antes los valores");
                    }
                    break;
                case 3:
                    if(Operador!=null && calculadora1!=null){
                        try {
                            opcion2 = 0;
                            while (opcion2!=1) {
                                resultado = Calculadora.Operacion(calculadora1);
                                if (calculadora1.getoperando2() == 0){
                                    opcion2=1;
                                }
                                else{
                                    if (resultado == resultado.intValue()){
                                        System.out.println("Resultado: "+ resultado.intValue());
                                    }
                                    else{
                                        System.out.println("Resultado: "+ resultado);
                                    }
                                    input=pedirpalabra(scanner, "Presiona Enter para continuar con la nueva operación o escribe 'salir' para finalizar:");
                                    if (input.equals("salir")) {
                                        opcion2=1;
                                    }
                                }
                            }
                            calculadora1 = null;
                            Operador = null;

                        } catch (Exception e) {
                            System.out.println("Ocurrió un error inesperado");
                        }
                    }
                    else{
                        System.out.println("Por favor, tiene que elegir los valores y el Operador primero");
                    }
                    break;
                case 4:
                default:
            }
            
        }
    }
}
