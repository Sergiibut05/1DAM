package ejercicio14;

public class FichaDomino {
    private int izq;
    private int dch;

    public FichaDomino(int izq, int dch){
        this.izq = izq;
        this.dch = dch;
    }

    public FichaDomino voltea(){
        int aux = izq;
        izq = dch;
        dch = aux;
        return this;
    }

    public boolean encaja(int numero){
        return dch==numero;
    }
    public boolean encaja(FichaDomino f){

        return izq == f.izq || 
               izq == f.dch || 
               dch == f.izq || 
               dch == f.dch;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s|%s]", 
            izq==0?" ":izq, 
            dch==0?" ":dch);
    }

    public int getDch() {
        return dch;
    }

    public int getIzq() {
        return izq;
    }
}
