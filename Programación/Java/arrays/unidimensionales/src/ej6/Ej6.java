package ej6;

public class Ej6 {
    public static int numAleatorio(){
        return (int)(Math.random()*601);
    }
    public static void main(String[] args) {
        int[] numeros = new int[15];
        for (int i = 0; i <= 14; i++){
            numeros[i] = numAleatorio();
        }
        System.out.println("Array original:");
        for (int i = 0; i <= 14; i++){
            System.out.printf("| %2d ", i);
        }
        System.out.println("|");
        System.err.println("----------------------------------------------------------------------------");
        for (int i = 0; i <= 14; i++){
            System.out.printf("|%3d ", numeros[i]);
        }
        System.err.println("|");
        System.err.println();
        System.out.println("Array rotado a la derecha una posición:");
        for (int i = 0; i <= 14; i++){
            System.out.printf("| %2d ", i);
        }
        System.out.println("|");
        System.err.println("----------------------------------------------------------------------------");
        int ultimoNumero = numeros[14];
        for(int i = 14; i > 0; i--){
            numeros[i] = numeros[i-1];
        }
        numeros[0] = ultimoNumero;
        for(int i = 0; i <= 14; i++){
            System.out.printf("|%3d ", numeros[i]);
        }
    }
}
