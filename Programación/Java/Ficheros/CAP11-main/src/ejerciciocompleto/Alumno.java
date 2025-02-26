package ejerciciocompleto;

public class Alumno implements Comparable<Alumno>, Serializable{
    private Integer id;
    private String apellidos;
    private String nombre;
    private String nie;
    private Integer grupoId;

    public Alumno(Integer id, String apellidos, String nombre, String nie, Integer grupoId){
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nie = nie;
        this.grupoId = grupoId;
    }

    public Integer getId() {
        return id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNie() {
        return nie;
    }

    public Integer getGrupoId() {
        return grupoId;
    }


    @Override
    public String toString() {
        return String.format(
            """
            ID: %d
            Apellidos: %s
            Nombre: %s
            NIE: %s
            Grupo: %d
            """, id, apellidos, nombre, nie, grupoId );
    }


    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!this.getClass().equals(obj.getClass()))
            return false;
        return this.id == ((Alumno)obj).id;
    }

    @Override
    public int compareTo(Alumno o) {
        int compare = this.apellidos.compareTo(o.apellidos);
        if(compare == 0)
            return this.nombre.compareTo(o.nombre);
        return compare;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String serialize() {
        return String.format("%d;%s;%s;%s;%d",id, apellidos, nombre, nie, grupoId);
    }
    
    
}
