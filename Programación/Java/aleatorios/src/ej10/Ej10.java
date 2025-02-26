package ej10;

public class Ej10 {
    public static void pintarLinea(){
        char simbolo = (char)('0' + (int)(Math.random()*6));
        switch (simbolo) {
            case '0':
                simbolo = '*';
                break;
            case '1':
                simbolo = '-';
                break;
            case '2':
                simbolo = '=';
                break;
            case '3':
                simbolo = '.';
                break;
            case '4':
                simbolo = '|';
                break;
            case '5':
                simbolo = '@';
                break;
        
            default:
                break;
        };
        int veces = numRepeticiones();
        for (int i = 0; i <= veces; i++){
        System.out.printf("%s", simbolo);
        }
        System.out.println();
    }

    public static int numRepeticiones(){
        int num = (int)(Math.random()*40);
        return num;
    }
    public static void main(String[] args) {
        for (int i = 1; i <=10; i++){
            pintarLinea();
        }
    }
}
