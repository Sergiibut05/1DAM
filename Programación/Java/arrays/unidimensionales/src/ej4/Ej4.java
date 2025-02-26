package ej4;

public class Ej4 {
    public static int numAleatorio(){
        return (int)((Math.random()*100)+1);
    }
    public static void main(String[] args) {
        int[] numeroBase = new int[20];
        int[] numeroCuadrado = new int[20];
        int[] numeroCubo = new int[20];
        for (int i = 0; i <= 19; i++){
            numeroBase[i] = numAleatorio();
        }
        for (int i = 0; i <= 19; i++){
            numeroCuadrado[i] = (int)Math.pow(numeroBase[i], 2);
        }
        for (int i = 0; i <= 19; i++){
            numeroCubo[i] = (int)Math.pow(numeroBase[i], 3);
        }
        System.out.println("  n  |   n2  |     n3   ");
        System.out.println("------------------------");
        for (int i = 1; i <= 19; i++){
            System.out.printf(" %3d | %5d | %7d %n", numeroBase[i], numeroCuadrado[i], numeroCubo[i]);
        }
    }
}
