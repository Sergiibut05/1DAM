package ej9;

public class Ej9 {
    public static void main(String[] args) {
        
        final int TAMANIO = 24;
        
        // Crear y rellenar la matriz original
        int[][] matriz = new int[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                matriz[i][j] = (int)(Math.random() * 101);
            }
        }
        
        // Bucle infinito para rotar continuamente
        while (true) {
            // Limpiar pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            // Mostrar matriz actual
            System.out.println("Array original:");
            System.out.println("---------------");
            mostrarMatriz(matriz);
            
            // Crear matriz para el resultado
            int[][] resultado = new int[TAMANIO][TAMANIO];
            
            // Para cada capa
            for (int capa = 0; capa < TAMANIO/2; capa++) {
                int n = TAMANIO - 2 * capa;
                int[] temp = new int[n * 4 - 4];
                int idx = 0;
                
                // Copiar elementos a array temporal
                for (int j = capa; j < TAMANIO-capa; j++) 
                    temp[idx++] = matriz[capa][j];
                for (int i = capa+1; i < TAMANIO-capa; i++) 
                    temp[idx++] = matriz[i][TAMANIO-1-capa];
                for (int j = TAMANIO-2-capa; j >= capa; j--) 
                    temp[idx++] = matriz[TAMANIO-1-capa][j];
                for (int i = TAMANIO-2-capa; i > capa; i--) 
                    temp[idx++] = matriz[i][capa];
                
                // Rotar array temporal
                int ultimo = temp[temp.length-1];
                for (int i = temp.length-1; i > 0; i--) {
                    temp[i] = temp[i-1];
                }
                temp[0] = ultimo;
                
                // Colocar elementos rotados en matriz resultado
                idx = 0;
                for (int j = capa; j < TAMANIO-capa; j++) 
                    resultado[capa][j] = temp[idx++];
                for (int i = capa+1; i < TAMANIO-capa; i++) 
                    resultado[i][TAMANIO-1-capa] = temp[idx++];
                for (int j = TAMANIO-2-capa; j >= capa; j--) 
                    resultado[TAMANIO-1-capa][j] = temp[idx++];
                for (int i = TAMANIO-2-capa; i > capa; i--) 
                    resultado[i][capa] = temp[idx++];
            }
            
            // Pequeña pausa
            try {
                Thread.sleep(200); // Pausa de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // La matriz resultado se convierte en la nueva matriz original
            matriz = resultado;
        }
    }
    
    private static void mostrarMatriz(int[][] matriz) {
        final String RESET = "\u001B[0m";
        final String WHITE = "\u001B[48;5;255m";
        final String BLACK = "\u001B[30m";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                String color = ((i+j) % 2 == 1) ? WHITE : BLACK;
                System.out.print(color + "   " + RESET);
            }
            System.out.println();
        }
    }
} 