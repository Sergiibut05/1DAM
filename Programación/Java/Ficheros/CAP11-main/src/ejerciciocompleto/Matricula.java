package ejerciciocompleto;

public class Matricula implements Comparable<Matricula>{
    private Integer id;
    private Integer alumnoId;
    private Integer materiaId;
    
    Matricula(Integer id, Integer alumnoId, Integer materiaId){
        this.id = id;
        this.alumnoId = alumnoId;
        this.materiaId = materiaId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public Integer getMateriaId() {
        return materiaId;
    }


    @Override
    public String toString() {
        return String.format("""
                ID: %d
                IDAlumno: %d
                IDMateria: %d
                """, id, alumnoId, materiaId);
        
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!this.getClass().equals(obj.getClass()))
            return false;
        return this.materiaId == ((Matricula)obj).materiaId &&
               this.alumnoId == ((Matricula)obj).alumnoId;
    }
    @Override
    public int compareTo(Matricula o) {
        return this.materiaId.compareTo(o.materiaId);
    }



}
