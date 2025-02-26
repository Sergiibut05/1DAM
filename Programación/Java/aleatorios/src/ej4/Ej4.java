package ej4;

public class Ej4 {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++){
            final int MIN = 0;
            final int MAX = 10;
            int num = (int)(Math.random()*(MAX-MIN+1)+MIN);
            System.out.printf("%s ", num);
        }
    }
}
