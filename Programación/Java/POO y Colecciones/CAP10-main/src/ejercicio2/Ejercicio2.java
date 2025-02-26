package ejercicio2;
import java.util.ArrayList;
public class Ejercicio2 {
    
    
        public static void main(String[] args) {
            ArrayList<Integer> numeros = new ArrayList<Integer>();
            int cantidad = (int)(Math.random()*20 + 10);
            for(int i=0; i < cantidad; i++)
                numeros.add((int)(Math.random()*101));
            System.out.println(numeros);
            int suma = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(Integer numero : numeros){
                suma += numero;
                if(numero > max)
                    max = numero;
                if(numero < min)
                    min = numero;
            }
            System.out.printf("Suma: %d%n", suma);
            System.out.printf("Media: %.2f%n", (double)suma/numeros.size());
            System.out.printf("Mínimo: %d%n", min);
            System.out.printf("Máximo: %d%n", max);
            
    }
}
