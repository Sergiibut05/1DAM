package ej5;

public class Ej5 {
    public static int numAleatorio(){
        return (int)(Math.random()*1001);
    }

    public static void main(String[] args) {
        int[][] numeros = new int[6][10];
        System.out.print("      ");
        for(int i = 0; i <= 9; i++){
            System.out.printf("    %d ", i);
        }
        System.out.println();
        System.out.println("    ---------------------------------------------------------------");
        for(int i = 0; i <= 5; i++){
            System.out.printf("  %d |", i);
            for(int j = 0; j <= 9; j++){
                numeros[i][j] = numAleatorio();
                System.out.printf("  %4d", numeros[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("    ---------------------------------------------------------------");
        int max = 0;
        int min = 999;
        int colMin = 0;
        int colMax = 0;
        int filMin = 0;
        int filMax = 0;
        for(int i = 0; i <= 5; i++){
            for(int j = 0; j <= 9; j++){
                if (numeros[i][j] > max) {
                    max = numeros[i][j];
                    filMax = i;
                    colMax = j;
                }else if(numeros[i][j] < min){
                    min = numeros[i][j];
                    filMin = i;
                    colMin = j;
                }
            }
        }
        System.out.println();
        System.out.printf("El máximo es %d y está en la fila %d, columna %d%n", max, filMax, colMax);
        System.out.printf("El mínimo es %d y está en la fila %d, columna %d", min, filMin, colMin);
    }
}
