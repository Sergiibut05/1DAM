package ej8;

public class Ej8 {
    public static void main(String[] args) {
        System.out.println("Introduzca la posición del alfil (ej: d5): ");
        String posicion = System.console().readLine();
        
        // Convertir letra de columna a índice de matriz
        char anchura = posicion.charAt(0);
        int posicionHorizontal = 0;
        switch (anchura) {
            case 'a': posicionHorizontal = 0; break;
            case 'b': posicionHorizontal = 1; break;
            case 'c': posicionHorizontal = 2; break;
            case 'd': posicionHorizontal = 3; break;
            case 'e': posicionHorizontal = 4; break;
            case 'f': posicionHorizontal = 5; break;
            case 'g': posicionHorizontal = 6; break;
            case 'h': posicionHorizontal = 7; break;
        }
        
        // Convertir fila de cadena a índice de matriz
        int posicionVertical = Character.getNumericValue(posicion.charAt(1)) - 1; // Convierte '1'-'8' a 0-7
        
        System.out.println("El alfil puede moverse a las siguientes posiciones:");
        
        // Movimientos diagonales (derecha arriba, izquierda abajo, derecha abajo, izquierda arriba)
        for (int i = 1; i < 8; i++) {
            // Derecha arriba
            if (posicionHorizontal + i < 8 && posicionVertical + i < 8) {
                System.out.print((char)(anchura + i) + "" + (posicionVertical + i + 1) + " ");
            }
            // Izquierda abajo
            if (posicionHorizontal - i >= 0 && posicionVertical - i >= 0) {
                System.out.print((char)(anchura - i) + "" + (posicionVertical - i + 1) + " ");
            }
            // Derecha abajo
            if (posicionHorizontal + i < 8 && posicionVertical - i >= 0) {
                System.out.print((char)(anchura + i) + "" + (posicionVertical - i + 1) + " ");
            }
            // Izquierda arriba
            if (posicionHorizontal - i >= 0 && posicionVertical + i < 8) {
                System.out.print((char)(anchura - i) + "" + (posicionVertical + i + 1) + " ");
            }
        }
        System.out.println();
    }
}
