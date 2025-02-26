package ejercicio2;

import ejercicio2.vehiculos.Vehiculo;
import ejercicio2.vehiculos.Coche;
import ejercicio2.vehiculos.Bicicleta;

public class Ejercicio2 {
    private static void menu(){
        System.out.println("""
                1. Anda con la bicicleta
                2. Haz el caballito con la bicicleta
                3. Anda con el coche
                4. Quema rueda con el coche
                5. Ver kilometraje de la bicicleta
                6. Ver kilometraje del coche
                7. Ver kilometraje total
                8. Salir
                """);
        
    }
    private static int leerOpcion(){
        return Integer.parseInt(System.console().readLine());
    }

    private static int solicitaKms(){
        System.out.print("¿Cuántos kms desea recorrer?");
        return Integer.parseInt(System.console().readLine());
    }

    public static void main(String[] args) {
        int opcion = 0;
        Bicicleta bicicleta = new Bicicleta();
        Coche coche = new Coche();
        do {
            menu();
            opcion=leerOpcion();
            switch (opcion) {
                case 1:
                    bicicleta.anda(solicitaKms());
                    break;
                case 2:
                    System.out.println(bicicleta.hazCaballito());
                    break;
                case 3:
                    coche.anda(solicitaKms());
                    break;
                case 4:
                    System.out.println(coche.quemaRuedas());
                    break;
                case 5:
                    System.out.printf("La bicicleta lleva recorridos %d kms", bicicleta.getKilometros());
                    break;
                case 6:
                    System.out.printf("El coche lleva recorridos %d kms", coche.getKilometros());
                    break;
                case 7:
                System.out.printf("Los vehículos llevan recorridos %d kms", Vehiculo.getKilometrosTotales());
                    break;
                default:
                    break;
            }
        } while (opcion != 8);
    }
}
