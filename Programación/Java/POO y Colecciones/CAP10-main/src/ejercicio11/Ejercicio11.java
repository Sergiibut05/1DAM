package ejercicio11;

import ejercicio10.NotInDictionaryException;
import ejercicio10.TranslateService;

import java.util.ArrayList;
import java.util.Map;
public class Ejercicio11 {
    static TranslateService translate = new TranslateService();

    public static void main(String[] args) {
        translate.addTranslation("palabra", "word");
        translate.addTranslation("mes", "month");
        translate.addTranslation("día", "day");
        translate.addTranslation("año", "year");
        translate.addTranslation("correr", "run");
        translate.addTranslation("pelota", "ball");
        translate.addTranslation("oscuro", "dark");
        translate.addTranslation("claro", "light");
        translate.addTranslation("suave", "smooth");
        translate.addTranslation("sol", "sun");
        translate.addTranslation("coche", "car");
        translate.addTranslation("compañía", "company");
        translate.addTranslation("negocios", "business");
        translate.addTranslation("emprendedor", "entrepeneur");
        translate.addTranslation("lluvia", "rain");
        translate.addTranslation("gato", "cat");
        translate.addTranslation("perro", "dog");
        translate.addTranslation("casa", "house");
        translate.addTranslation("presidente", "president");
        translate.addTranslation("ayuntamiento", "town hall");
        translate.addTranslation("motocicleta", "motorcycle");
        translate.addTranslation("programa", "program");
        translate.addTranslation("programador", "developer");
        ArrayList<String> words = new ArrayList<String>();
        int wordCount = 0;
        for( Map.Entry<String,String> entry:translate.getDictionary()){
            words.add(entry.getKey());
            if(++wordCount==5)
                break;
        }
        for(int i = 0; i < words.size(); i++){
            System.out.printf("Traduce al ingles %s: ", words.get(i));
            String translation = System.console().readLine();
            try {
                if(translate.isTranslationOf(words.get(i), translation))
                    System.out.println("Es correcto!!!!");
                else
                    System.out.println("Tienes que aprender más inglés");
            } catch (NotInDictionaryException e) {
                System.out.println("Ocurrió un error inesperado");
            }
            
        }

    }
    
}
