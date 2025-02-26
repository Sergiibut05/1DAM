package ej9;

public class Ej9 {
    public static int num(){
        int numero = (int)(Math.random()*101);
        return numero;
    }
    public static void main(String[] args) {
        int numero = num();
        int contador = 0;
        while (numero != 24) {
            System.out.printf("%d ", numero);
            numero = num(); 
            contador++;
        }
        System.out.println("24");
        contador++;
        System.out.printf("Se han generado %d números", contador);
    }
}
