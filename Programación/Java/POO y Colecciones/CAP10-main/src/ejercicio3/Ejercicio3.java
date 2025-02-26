package ejercicio3;

import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio3 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        for(int i = 1; i<=10;i++){
            System.out.print("Introduce un número: ");
            Integer numero = Integer.parseInt(System.console().readLine());
            numeros.add(numero);
        }
        System.out.println("Antes de ordenar: \n"+numeros);
        Collections.sort(numeros);
        System.out.println("Después de ordenar: \n"+numeros);
    }
    
}
