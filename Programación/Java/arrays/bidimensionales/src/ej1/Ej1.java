package ej1;

public class Ej1 {
    public static void main(String[] args) {
        int[][] num = new int[3][6];
        num[0][0] = 0;
        num[0][1] = 30;
        num[0][2] = 2;
        num[0][5] = 5;
        num[1][0] = 75;
        num[1][4] = 0;
        num[2][2] = -2;
        num[2][3] = 9;
        num[2][5] = 11;
        System.out.print("         ");
        for(int i = 0; i <= 5; i++){
            System.out.printf("Columna %d   ", i);
        }
        System.out.println();
        for(int i = 0; i <= 2; i++){
            System.out.printf("Fila %d   ", i);
            for(int j = 0; j <= 5; j++){
                System.out.printf("   %2d       ", num[i][j]);
            }
            System.out.println();
        }
    }
}
