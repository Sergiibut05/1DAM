package ej12;

public class Ej12 {

    public static void main(String[] args) {
        int[][] matriz = new int[9][9];
        for(int i = 0; i <= 8; i++){
            for(int j = 0; j <= 8; j++){
                matriz[i][j] = (int)(Math.random()*(999-500+1))+500;
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        int[] diagonal = new int[9];
        int contDiagonal = 0;
        System.out.println();
        for(int i = 8; i >= 0; i--){
            diagonal[contDiagonal] = matriz[i][contDiagonal];
            contDiagonal++;
        }
        int max = 500;
        int min = 1000;
        int suma = 0;
        System.out.println("Diagonal desde la esquina inferior izquierda a la esquina superior derecha:");
        for (int i = 0; i <= 8; i++){
            System.out.print(diagonal[i] + " ");
            max = Math.max(max,diagonal[i]);
            min = Math.min(min,diagonal[i]);
            suma += diagonal[i];
        }
        System.out.println();
        System.out.println("Máximo: " + max);
        System.out.println("Mínimo: " + min);
        System.out.println("Media: " + suma / 9);
    }
}
