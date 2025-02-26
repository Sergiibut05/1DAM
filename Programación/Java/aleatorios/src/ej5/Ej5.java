package ej5;

public class Ej5 {
    public static void main(String[] args) {
        final int MAX_NUMEROS = 50;
        final int MIN_NUM = 100;
        final int MAX_NUM = 199;
        int max = 0;
        int min = MAX_NUM+1;
        int media = 0;
        for (int i = 1; i <= MAX_NUMEROS; i++){
            int num = 0;
            num = (int)(Math.random()*(MAX_NUM-MIN_NUM+1)+MIN_NUM);
            System.out.printf("%d ", num);
            max = Math.max(max, num);
            min = Math.min(min, num);
            media+=num;
        }
        System.out.println();
        System.out.printf("Mínimo: %d%n", min);
        System.out.printf("Máximo: %d%n", max);
        System.out.printf("Media: %d", media/MAX_NUMEROS);

    }
}
