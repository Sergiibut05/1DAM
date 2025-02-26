package ej1;

public class Ej1 {
    public static void main(String[] args) {
        int numValores = 12;
        int[] num = new int[numValores];
        num[0] = 39;
        num[1] = -2;
        num[4] = 0;
        num[6] = 14;
        num[8] = 5;
        num[9] = 120;
        for (int i = 0; i <= numValores-1; i++){
            System.out.printf("%s%n", num[i]);
        }
    }
}
