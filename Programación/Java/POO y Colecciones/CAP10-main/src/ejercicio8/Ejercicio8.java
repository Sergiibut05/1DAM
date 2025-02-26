package ejercicio8;

import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio8 {
    public static void main(String[] args) {
        ArrayList<Carta> baraja = new ArrayList<Carta>();
        for(int i = 1; i<=40;i++)
            baraja.add(new Carta(i));
        Collections.shuffle(baraja);
        ArrayList<Carta> mano1 = new ArrayList<>();
        mano1.add(baraja.remove(0));
        mano1.add(baraja.remove(0));
        mano1.add(baraja.remove(0));
        ArrayList<Carta> mano2 = new ArrayList<>();
        mano2.add(baraja.remove(0));
        mano2.add(baraja.remove(0));
        mano2.add(baraja.remove(0));
        ArrayList<Carta> mano3 = new ArrayList<>();
        mano3.add(baraja.remove(0));
        mano3.add(baraja.remove(0));
        mano3.add(baraja.remove(0));

        ArrayList<Carta> mano4 = new ArrayList<>();
        mano4.add(baraja.get(0));
        mano4.add(baraja.get(0));
        mano4.add(baraja.get(0));

        Collections.sort(baraja);
        System.out.println("Mazo de cartas\n"+baraja);
        System.out.println("Cartas del jugador 1\n"+mano1);
        System.out.println("Cartas del jugador 2\n"+mano2);
        System.out.println("Cartas del jugador 3\n"+mano3);
        System.out.println("Cartas del jugador 4\n"+mano4);

        

    }
    
}
