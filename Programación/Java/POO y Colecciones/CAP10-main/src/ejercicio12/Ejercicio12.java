package ejercicio12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio12 {
    public static void main(String[] args) {
        ArrayList<CartaBrisca> baraja = new ArrayList<CartaBrisca>();
        for(int i = 1; i<=40;i++)
            baraja.add(new CartaBrisca(i));
        Collections.shuffle(baraja);
        CartaBrisca carta = new CartaBrisca(0);
        HashMap<CartaBrisca, Integer> mano = new HashMap<>();
        for(int i = 0; i < 5; i++){
            carta = baraja.remove(0);
            mano.put(carta, carta.getValor());
        }
        
        int puntos = 0;
        for(Map.Entry<CartaBrisca, Integer> entry:mano.entrySet()){
            System.out.printf("%s%n", entry.getKey());
            puntos+=entry.getValue();
        }
        System.out.printf("Tienes %d puntos", puntos);
    }
    
}
