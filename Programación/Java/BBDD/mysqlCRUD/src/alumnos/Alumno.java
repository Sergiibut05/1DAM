package alumnos;
public class Alumno implements MySerializer{
    long id;
    String nombre;
    String apellidos;
    Long grupoId;
 
    public Alumno(){
        this(0,"","", null);
    }

    public Alumno(Alumno al){
        this.id = al.id;
        this.nombre = al.nombre;
        this.apellidos = al.apellidos;
        this.grupoId = al.grupoId;
    }
    
    public Alumno(String data){
        deserialize(data);
    }

    public Alumno(long id, String nombre, String apellidos, Long grupoId){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.grupoId = grupoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Apellidos: %s", this.id, this.nombre, this.apellidos);
    }

    @Override
    public String serialize() {
        return String.format("\"%d\";\"%s\";\"%s;%s", this.id, this.nombre, this.apellidos, this.grupoId!=null?"\""+this.grupoId.toString()+"\"":"NULL");
    }

    private String substractQuotes(String data){
        return data.substring(1, data.length()-1);
    }

    @Override
    public void deserialize(String data) {
        String[] datos = data.split(";");
        
        this.id = Integer.parseInt(datos[0].substring(1, datos[0].length()-1));
        this.nombre = this.substractQuotes(datos[1]);
        this.apellidos = this.substractQuotes(datos[2]);
        
        if(datos[3].equals("NULL"))
            this.grupoId = null;
        else
            this.grupoId = Long.parseLong(this.substractQuotes(datos[3]));
        }
}
