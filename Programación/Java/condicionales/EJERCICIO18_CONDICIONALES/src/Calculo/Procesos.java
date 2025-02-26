package Calculo; 
public class Procesos {
    public static void ordenar(int numero){
        try {
            if (numero > 0 && numero < 100000) {
                // Extraer la primera cifra
                int primeraCifra = numero;
                while (primeraCifra >= 10) {
                    primeraCifra /= 10; // Dividir entre 10 hasta obtener la primera cifra
                }
                System.out.println("La primera cifra del número introducido es el " + primeraCifra + ".");
            } else {
                System.out.println("Por favor, asegúrese de que el número sea positivo y tenga hasta 5 cifras.");
            }
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
