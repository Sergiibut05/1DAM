package ejercicio19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio19{
    public static void main(String[] args) {
        HashMap<String, String> diccionario = new HashMap<>();
        diccionario.put("caliente", "hot");
        diccionario.put("rojo", "red");
        diccionario.put("ardiente", "hot");
        diccionario.put("verde", "green");
        diccionario.put("agujetas", "stiff");
        diccionario.put("abrasador", "hot");
        diccionario.put("hierro", "iron");
        diccionario.put("grande", "big");
        String palabra = "";
        do {
            System.out.print("Introduzca una palabra y le daré los sinónimos: ");
            palabra = System.console().readLine();
            if(diccionario.containsKey(palabra)){
                ArrayList<String> sinonimos = new ArrayList<>();
                String traduccion = diccionario.get(palabra);
                for(Map.Entry<String, String> entry:diccionario.entrySet())
                    if(entry.getValue().equals(traduccion) && !entry.getKey().equals(palabra))
                        sinonimos.add(entry.getKey());
                if(sinonimos.size()>0){
                    System.out.printf("Sinónimos de %s: ", palabra);
                    for(String sinonimo:sinonimos)
                        System.out.printf("%s, ", sinonimo);
                    System.out.println();
                }
                else
                    System.out.println("No conozco sinónimos de esa palabra");
            }
            else{
                System.out.println("No conozco esa palabra");
            }
            
        } while (!palabra.equals("salir"));
    }
}