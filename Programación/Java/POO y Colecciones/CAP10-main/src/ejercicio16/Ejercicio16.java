package ejercicio16;

import java.util.HashMap;

public class Ejercicio16 {

    public static void main(String[] args) {
        HashMap<String, String> capitales = new HashMap<>();
        capitales.put("España", "Madrid");
        capitales.put("Portugal", "Lisboa");
        capitales.put("Francia", "Paris");
        String pais = "";
        do {
            System.out.print("Escribe el nombre de un país y te diré su capital: ");
            pais = System.console().readLine();
            
            if(capitales.containsKey(pais)){
                String capital = capitales.get(pais);
                System.out.printf("La captial de %s es %s%n", pais, capital);
            }
            else{
                System.out.printf("No conozco la respuesta ¿cuál es la capital de %s?: ", pais);
                String capital = System.console().readLine();
                capitales.put(pais, capital);
                System.out.println("Gracias por enseñarme nuevas capitales");
            }
        } while (!pais.toLowerCase().equals("salir"));
    }
}
