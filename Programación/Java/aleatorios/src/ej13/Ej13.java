package ej13;

public class Ej13 {
    public static int tirada(){
        int resultado = (int)(((Math.random()*6-1)+1)+1);
        return resultado;
    }
    public static void main(String[] args) {
        int tirada1 = 0;
        int tirada2 = 1;
        while (tirada1 != tirada2) {
            tirada1 = tirada();
            tirada2 = tirada();
            System.out.printf("%d %d%n", tirada1, tirada2);
        }
    }
}
