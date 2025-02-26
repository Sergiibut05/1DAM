package ejercicio21;

import java.util.HashMap;

public class Ejercicio21 {
    public static void main(String[] args) {
        HashMap<String, String> habitats = new HashMap<>();
        HashMap<String, String> alimentacion = new HashMap<>();
        habitats.put("rana", "En los trópicos y cerca de las zonas húmedas y acuáticas");
        habitats.put("salamandra", "Ecosistemas húmedos");
        habitats.put("sapo", "En cualquier sitio salvo en el desierto y la Antártida");
        habitats.put("tritón", "América y África");
        alimentacion.put("rana", "Larvas e insectos");
        alimentacion.put("salamndra", "Pequeños crustáceos e insectos");
        alimentacion.put("sapo", "Insectos, lombrices y pequeños roedores");
        alimentacion.put("tritón", "Insectos");
        System.out.print("Introduzca el tipo de anfibio: ");
        String tipo = System.console().readLine();
        if(habitats.containsKey(tipo) && alimentacion.containsKey(tipo)){
            System.out.printf("Hábitat: %s%n", habitats.get(tipo));
            System.out.printf("Alimentación: %s%n", alimentacion.get(tipo));
        }
        else{
            System.out.println("No reconozco ese tipo de anfibio");
        }
    }
    
}
