package ej12;

public class Ej12 {
    public static char caracter (){
        char simbolo = (char)('0' + (int)(Math.random()*132-32+1+32));
        return simbolo;
    }
    public static void main(String[] args) {
        boolean uwu = true;
        while (uwu) {
            System.out.print(caracter());
        }
    }
}
