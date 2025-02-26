package ej11;

public class Ej11 {
    public static boolean esPrimo(int num){
        if (num < 2) return false;
        for (int i = 2; i <= num / 2; i++){
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int numAleatorio(){
        return (int)(Math.random() * 10);
    }

    public static void main(String[] args) {
        System.out.println("Array original:");
        System.out.print("\n" + "| Índice ");
        for (int i = 0; i <= 9; i++){
            System.out.printf("|   %d ", i);
        }
        System.out.println("|");
        System.out.println("----------------------------------------------------------------------");

        int[] numeros = new int[10];
        System.out.print("| Valor  ");
        int contPrimos = 0;
        for (int i = 0; i <= 9; i++){
            numeros[i] = numAleatorio();
            if (esPrimo(numeros[i])) {
                contPrimos++;
            }
            System.out.printf("| %3d ", numeros[i]);
        }
        System.out.println("|" + "\n" + "\n");

        System.out.println("Array con los primos al principio:");
        System.out.print("\n" + "| Índice ");
        for (int i = 0; i <= 9; i++){
            System.out.printf("|   %d ", i);
        }
        System.out.println("|");
        System.out.println("----------------------------------------------------------------------");

        int[] noPrimos = new int[10 - contPrimos];
        int[] primos = new int[contPrimos];
        int contadorPrimos = 0;
        int contNoPrimos = 0;
        for (int i = 0; i <= 9; i++){
            if (esPrimo(numeros[i])) {
                primos[contadorPrimos++] = numeros[i];
            } else {
                noPrimos[contNoPrimos++] = numeros[i];
            }
        }

        System.out.print("| Valor  ");
        for (int i = 0; i < contNoPrimos; i++){
            System.out.printf("| %3d ", noPrimos[i]);
        }
        for (int i = 0; i < contadorPrimos; i++){
            System.out.printf("| %3d ", primos[i]);
        }
        System.out.println("|");
    }
}
