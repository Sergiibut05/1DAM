package ej17;

public class Ej17 {
    public static void pintarCuadrado(int altura, int anchura){
        int alturaPez = 2 + (int)(Math.random() * (altura - 3));
        int anchuraPez = 2 + (int)(Math.random() * (anchura - 3));
        for (int i = 1; i<= altura; i++){
            for (int j = 1; j<=anchura; j++){
                if (i == 1 || j == 1 | i == altura || j == anchura) {
                    System.out.print("*");
                }else if (i == alturaPez && j == anchuraPez){
                    System.out.print("&");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.print("Por favor, introduzca la altura de la pecera (como mínimo 4): ");
        int altura = Integer.parseInt(System.console().readLine());
        System.out.print("Ahora introduzca la anchura (como mínimo 4): ");
        int anchura = Integer.parseInt(System.console().readLine());
        pintarCuadrado(altura, anchura);
    }
}



