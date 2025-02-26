package ejercicio17;

public class Piramide {
    private int altura;
    private static int piramidesCreadas;

    public Piramide(int altura){
        this.altura = altura;
        piramidesCreadas++;
    }

    public static int getPiramidesCreadas() {
        return piramidesCreadas;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i=1;i<=altura; i++){
            for(int j=0; j<altura-i; j++)
                res+=" ";
            for(int j=0; j<2*i-1; j++)
                res +="*";
            res+="\n";
        }
        return res;
    }
}
