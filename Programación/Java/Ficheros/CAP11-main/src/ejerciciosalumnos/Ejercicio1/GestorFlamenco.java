package ejerciciosalumnos.Ejercicio1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorFlamenco implements GFOperations{
    private FileManager fileManager;
    private ArrayList<FraseFlamenca> frases;
    private HashMap<FraseFlamenca, Integer> hashFrases; 
    private Integer idMax = 0;

    public GestorFlamenco(String fileName) throws IOException{
        fileManager = new FileManager(fileName);
        frases = new ArrayList<>();
        hashFrases = new HashMap<>();
        for(String linea:fileManager.leer()){
            FraseFlamenca frase = FraseSerializable.deserialize(linea);
            if(frase.getId()> idMax)
                idMax = frase.getId();
            frases.add(frase);
            hashFrases.put(frase, frase.getFrase().length());
        }
    }

    public FraseFlamenca anadeFrase(String frase){
        FraseFlamenca fraseFlamenca = new FraseFlamenca(++idMax, frase);
        if(!frases.contains(fraseFlamenca))
            frases.add(fraseFlamenca);
        else
            idMax--;
        hashFrases.put(fraseFlamenca, fraseFlamenca.getFrase().length());
        return fraseFlamenca;
    }

    public boolean eliminaFrase(Integer id){
        FraseFlamenca frase = new FraseFlamenca(id, null);
        int index = frases.indexOf(frase);
        if(index!=-1){
            frases.remove(index);
            
            return hashFrases.remove(frase)!=null;
        }
        return false;
    }

    public FraseFlamenca obtenerPorID(Integer id){
        int index = frases.indexOf(new FraseFlamenca(id, null));
        return (index!=-1)?frases.get(index):null;
    }

    public List<FraseFlamenca> listar(){
        ArrayList<FraseFlamenca> lista = new ArrayList<>();
        for(FraseFlamenca frase:frases)
            lista.add(frase);
        return lista;
    }

    public boolean guardar(){
        ArrayList<String> frasesFlamencas = new ArrayList<>();
        for(FraseFlamenca frase: frases){
            frasesFlamencas.add(frase.serialize());
        }
        try {
            fileManager.escribir(frasesFlamencas);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean borrarFichero(){
        try {
            fileManager.borrarArchivo();
            return true;    
        } catch (Exception e) {
            return false;
        }
    }
}