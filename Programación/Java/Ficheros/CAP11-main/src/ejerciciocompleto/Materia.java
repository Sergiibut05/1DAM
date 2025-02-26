package ejerciciocompleto;

public class Materia implements Comparable<Materia>{

    private Integer id;
    private String nombre;

    public Materia(Integer id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Nombre: %s        
                """, id, nombre);
    }

    @Override
    public int compareTo(Materia o) {
        return this.nombre.compareTo(o.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!this.getClass().equals(obj.getClass()))
            return false;
        return this.id == ((Materia)obj).id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    
}
