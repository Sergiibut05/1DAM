package ejSumaFilasYColumnas;

public class EjSumaFilasYColumnas {
    private static int[][] rellenarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                matriz[i][j] = (int)(Math.random()*(8024-1493)+1492);
            }
        }
        return matriz;
    }

    private static void pintarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                System.out.printf("%-4d    ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    private static void pintarArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.printf("%-6d  ", array[i]);
        }
    }

    private static int[] sumarFilas(int[][] matriz){
        int[] sumaFilas = new int[matriz.length];
        for(int i = 0; i < matriz.length; i++){
            int suma = 0;
            for(int j = 0; j < matriz[i].length; j++){
                suma += matriz[i][j];
            }
            sumaFilas[i] = suma;
        }
        return sumaFilas;
    }

    private static int[] sumarColumnas(int[][] matriz){
        int[] sumaColumnas = new int[matriz[0].length];
        for(int i = 0; i < matriz.length; i++){
            int suma = 0;
            for (int j = 0; j < matriz[i].length; j++){
                suma += matriz[j][i];
            }
            sumaColumnas[i] = suma;
        }
        return sumaColumnas;
    }

    public static void main(String[] args) {
        int[][] matriz = new int[5][5];
        matriz = rellenarMatriz(matriz);
        pintarMatriz(matriz);
        System.out.println();
        int[] sumaFilas = sumarFilas(matriz);
        pintarArray(sumaFilas);
        System.out.println();
        int[] sumaColumnas = sumarColumnas(matriz);
        pintarArray(sumaColumnas);
    }
}