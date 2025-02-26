package ej3;

public class Ej3 {
    public static int numAleatorio(){
        return (int)((Math.random()*100)+1);
    }
    public static void main(String[] args) {
        int[] lista = new int[10];
        for (int i = 1; i <= 9; i++){
            lista[i] = numAleatorio();
        }

        System.out.println("Lista bien");
        for (int i = 0; i <= 9; i++){
            System.out.println(lista[i]);
        }
        System.out.println();
        System.out.println("Lista del reves");
        for (int i = 9; i >= 1; i--){
            System.out.println(lista[i]);
        }
    }
}
