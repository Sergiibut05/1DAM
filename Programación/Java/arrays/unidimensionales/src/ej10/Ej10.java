package ej10;

public class Ej10 {
    public static int numAleatorio(){
        return (int)(Math.random()*101);
    }
    public static void main(String[] args) {
        System.out.println("Array original:");
        int[] numeros = new int[20];
        for (int i = 0; i <= 19; i++){
            numeros[i] = numAleatorio();
            System.out.print(numeros[i] + " ");
        }
        System.out.println("Array con los pares al principio:");
        int[] numerosPares = new int[20];
        int[] numerosImpares = new int[20];
        int contPares = 0;
        int contImpares = 0;
        for (int i = 0; i <= 19; i++){
            if (numeros[i] % 2 == 0){
                numerosPares[contPares] = numeros[i];
                contPares++;
            }else{
                numerosImpares[contImpares] = numeros[i];
                contImpares++;
            }
        }
        for (int i = 0; i <= contPares-1; i++){
            System.out.print(numerosPares[i] + " ");
        }
        for (int i = 0; i <= contImpares-1; i++){
            System.out.print(numerosImpares[i] + " ");
        }
    }
}
