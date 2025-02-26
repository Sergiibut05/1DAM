package ejercicio10;

public class Ameba implements AmebaInterface{

    private int peso = 3;

    @Override
    public void come(int peso) {
        this.peso += peso - 1;
    }

    @Override
    public void come(Ameba a) {
        this.peso += a.peso - 1;
        a.peso = 0;
    }

    @Override
    public String toString(){
        return String.format(
            "Soy una ameba y peso %d microgramos",
            peso);
    } 
    
}
