package Calculo; 
public class Procesos {
    public static void ordenar(int numero){
        try {
            if (numero > -100000 && numero < 100000) {
                int positivonum = Math.abs(numero);
                int contador = 1;
                while (positivonum >= 10) {
                    positivonum = positivonum/10;
                    contador = contador +1;
                }
                System.out.println("El número introducido tiene " + contador + " dígitos.");
            } else {
                System.out.println("Por favor, asegúrese de que el número sea entero y tenga hasta 5 cifras.");
            }
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
