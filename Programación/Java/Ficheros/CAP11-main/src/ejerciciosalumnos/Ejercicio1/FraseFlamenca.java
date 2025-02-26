package ejerciciosalumnos.Ejercicio1;


public class FraseFlamenca implements FraseSerializable, Comparable<FraseFlamenca>{
    private Integer id;
    private String frase;

    public FraseFlamenca(Integer id, String frase){
        this.id = id;
        this.frase = frase;
    }

    public String getFrase() {
        return frase;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!obj.getClass().equals(this.getClass()))
            return false;
        return this.id == ((FraseFlamenca)obj).getId();
    }

    @Override
    public String toString() {
        return String.format("ID: %d Frase: %s", id, frase);
    }

    @Override
    public String serialize() {
        return String.format("%d;%s", id, frase);
    
    }

    @Override
    public int compareTo(FraseFlamenca frase) {
        return this.frase.compareTo(frase.frase);
    }

   
}
