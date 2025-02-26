package ejerciciocompleto;

public abstract class AlumnoSerializable{

    public static String serialize(Alumno elem) {
        return elem.serialize();
    }

    public static Alumno deserialize(String object) {
        Integer id = Integer.parseInt(object.substring(0, object.indexOf(";")));
        object = object.substring(object.indexOf(";"), object.length());
        String apellidos = object.substring(0, object.indexOf(";"));
        object = object.substring(object.indexOf(";"), object.length());
        String nombre = object.substring(0, object.indexOf(";"));
        object = object.substring(object.indexOf(";"), object.length());
        String nie = object.substring(0, object.indexOf(";"));
        object = object.substring(object.indexOf(";"), object.length());
        Integer grupoId = Integer.parseInt(object);
        return new Alumno(id, apellidos, nombre, nie, grupoId);
    }

    
      
}
