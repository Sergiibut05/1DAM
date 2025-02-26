package ejercicio1;

import java.util.ArrayList;

public class Ejercicio1 {
    public static void main(String[] args) {
        ArrayList<String> gente = new ArrayList<String>();
        gente.add("Antonio Cano");
        gente.add("Antonio Salces");
        gente.add("Ana Isabel");
        gente.add("Guillermo Diañez");
        for(String persona:gente){
            System.out.println(persona);
        }    
    }
    
}
