package ejerciciosalumnos.Ejercicio1;

public interface FraseSerializable {
    public static String serialize(FraseFlamenca frase){
        return frase.toString();
    }
    public String serialize();
    public static FraseFlamenca deserialize(String object) throws NumberFormatException{
        Integer id = Integer.parseInt(object.substring(0, object.indexOf(";")));
        String frase = object.substring(object.indexOf(";")+1, object.length());
        return new FraseFlamenca(id, frase);
    }
}


