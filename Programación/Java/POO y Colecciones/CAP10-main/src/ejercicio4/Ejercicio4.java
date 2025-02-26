package ejercicio4;

import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio4 {
    public static void main(String[] args) {
        ArrayList<String> textos = new ArrayList<String>();
        for(int i = 1; i<=10;i++){
            System.out.print("Introduce un texto: ");
            String texto = System.console().readLine();
            textos.add(texto);
        }
        System.out.println("Antes de ordenar: \n"+textos);
        Collections.sort(textos);
        System.out.println("Después de ordenar: \n"+textos);
    }
    
}
