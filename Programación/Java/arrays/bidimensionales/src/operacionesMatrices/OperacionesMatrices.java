package operacionesMatrices;

public class OperacionesMatrices {
    private static void rellenarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                matriz[i][j] = (int)(Math.random()*10);
            }
        }
    }

    private static void pintarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                System.out.printf("%-3d  ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    private static int[][] multiplicarMatrices(int[][] m1, int[][] m2){
        int[][] m3 = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m2[0].length; j++){
                for(int k = 0; k < m1[0].length; k++){
                    m3[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return m3;
    }

    public static void main(String[] args) {
        int[][] m1 = new int[3][3];
        int[][] m2 = new int[3][3];
        rellenarMatriz(m1);
        System.out.println("Matriz 1:");
        pintarMatriz(m1);
        System.out.println("------------------");
        rellenarMatriz(m2);
        System.out.println("Matriz 2:");
        pintarMatriz(m2);
        System.out.println("------------------");
        int[][] m3 = multiplicarMatrices(m1, m2);
        System.out.println("Matriz resultante:");
        pintarMatriz(m3);
    }
}