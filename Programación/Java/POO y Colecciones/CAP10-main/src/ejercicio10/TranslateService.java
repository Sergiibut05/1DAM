package ejercicio10;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class TranslateService {

    HashMap<String, String> diccionario = new HashMap<String, String>();

    public void addTranslation(String word, String translation){
        diccionario.put(word, translation);
    }

    public void removeTranslation(String word) throws NotInDictionaryException{
        if(!diccionario.containsKey(word))
            throw new NotInDictionaryException();
        diccionario.remove(word);
    }

    public void updateTranslation(String word, String translation) throws NotInDictionaryException{
        if(!diccionario.containsKey(word))
            throw new NotInDictionaryException();
        addTranslation(word, translation);
    }

    public String translate(String word) throws NotInDictionaryException{
        if(!diccionario.containsKey(word))
            throw new NotInDictionaryException();
        return diccionario.get(word);
    }

    public boolean isTranslationOf(String word, String translation) throws NotInDictionaryException{
        if(!diccionario.containsKey(word))
            throw new NotInDictionaryException();
        return diccionario.get(word).equals(translation);
    }

    public Set<Map.Entry<String,String>> getDictionary(){
        return diccionario.entrySet();
    }
}
