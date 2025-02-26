package ej4;

public class Ej4 {
    public static void retardo(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int numAleatorio(){
        return (int)((((Math.random()*999)-100)+1)+100);
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
            retardo();
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
            retardo();
            System.out.printf("      %4d", columna1);
            retardo();
            System.out.printf("      %4d", columna2);
            retardo();
            System.out.printf("      %4d", columna3);
            retardo();
            System.out.printf("      %4d", columna4);
            retardo();
            System.out.printf("      %4d", columna5);
            retardo();
            retardo();
            System.out.printf("   |%7d", sumaTotal);
    }
}

