package ej5;

public class Ej5 {
    public static int numAleatorio(){
        return (int)((Math.random()*100000)-50000);
    }
    public static void main(String[] args) {
        int[] numeros = new int[10];
        for (int i = 0; i <= 9; i++){
            numeros[i] = numAleatorio();
        }
        int max = 0;
        int min = 0;
        for (int i = 0; i <= 9; i++){
            if (numeros[i] > max) {
                max = numeros[i];
            }else if(numeros[i] < min){
                min = numeros[i];
            }
        }
        for (int i = 0; i <= 9; i++){
            if (numeros[i] == max) {
                System.out.printf("%d máximo%n", numeros[i]);
            }else if (numeros[i] == min){
                System.out.printf("%d mínimo%n", numeros[i]);
            }else{
                System.out.println(numeros[i]);
            }
        }
    }
}
