package ej7;

public class Ej7 {
    public static void imprimirTabla(int[][] coordenada, boolean visible){
        int fila = 3;
        for(int i = 0; i <= 3; i++){
            System.out.print(fila + "|");
            for(int j = 0; j <= 4; j++){
                if (visible == true) {
                    if (coordenada[i][j] == 1) {
                        System.out.print("* ");
                    } else if (coordenada[i][j] == 2) {
                        System.out.print("E ");
                    } else if (coordenada[i][j] == 3) {
                        System.out.print("X ");
                    } else {
                        System.out.print("  ");
                    }
                }else if(coordenada[i][j] == 3){
                    System.out.print("X ");
                }
            }
            fila--;
            System.out.println();
        }
        System.out.println("  ----------");
        System.out.println("  0 1 2 3 4 ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] coordenada = new int[4][5];
        int alturaTesoro = (int)(Math.random() * 4); // Genera valores entre 0 y 3
        int anchuraTesoro = (int)(Math.random() * 5); // Genera valores entre 0 y 4
        coordenada[alturaTesoro][anchuraTesoro] = 1;

        int alturaMina = (int)(Math.random() * 4); // Genera valores entre 0 y 3
        int anchuraMina = (int)(Math.random() * 5); // Genera valores entre 0 y 4
        coordenada[alturaMina][anchuraMina] = 2;

        boolean visible = false;

        while (1!=0) {
            System.out.print("Coordenada x: ");
            int x = Integer.parseInt(System.console().readLine());
            System.out.print("Coordenada y: ");
            int y = Integer.parseInt(System.console().readLine());
            coordenada[x][y] = 3;
            if (coordenada[x][y] == coordenada[anchuraMina][alturaMina] || coordenada[x][y] == coordenada[anchuraTesoro][alturaTesoro]) {
                visible = true;
            }

            imprimirTabla(coordenada, visible);
        }
        
    }
}
