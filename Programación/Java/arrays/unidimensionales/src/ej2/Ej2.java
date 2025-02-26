package ej2;

public class Ej2 {
    public static void main(String[] args) {
        int numValores = 10;
        char[] simbolo = new char[numValores];
        simbolo[0] = 'a';
        simbolo[1] = 'x';
        simbolo[4] = '@';
        simbolo[6] = ' ';
        simbolo[7] = '+';
        simbolo[8] = 'Q';
        for (int i = 0; i <= numValores-1; i++){
            System.out.printf("%s%n", simbolo[i]);
        }
    }
}
