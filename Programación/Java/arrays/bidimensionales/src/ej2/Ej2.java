package ej2;

public class Ej2 {
    public static int numAleatorio(){
        return (int)(Math.random()*201);
    }
    
    public static void main(String[] args) {
        int[][] num = new int[4][5];
        for(int i = 0; i <= 3; i++){
            for(int j = 0; j <= 4; j++){
                num[i][j] = numAleatorio();
            }
        }
        int sumaTotal = 0;
        int columna1 = 0;
        int columna2 = 0;
        int columna3 = 0;
        int columna4 = 0;
        int columna5 = 0;
        for(int i = 0; i <= 3; i++){
            int suma = 0;
            for(int j = 0; j <= 4; j++){
                System.out.printf("       %3d", num[i][j]);
                suma+=num[i][j];
            }
            System.out.printf("   |%7d", suma);
            sumaTotal+=suma;
            suma = 0;
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------");
        for(int i = 0; i <= 3; i++){
            for(int j = 0; j <= 4; j++){
                if (j == 0) {
                    columna1+=num[i][j];
                }else if (j == 1){
                    columna2+=num[i][j];
                }else if (j == 2){
                    columna3+=num[i][j];
                }else if (j == 3){
                    columna4+=num[i][j];
                }else 
                    columna5+=num[i][j];
                }
            }
            System.out.printf("       %3d", columna1);
            System.out.printf("       %3d", columna2);
            System.out.printf("       %3d", columna3);
            System.out.printf("       %3d", columna4);
            System.out.printf("       %3d", columna5);
            System.out.printf("   |%7d", sumaTotal);
    }
}

