package ejercicio17;

public class Rectangulo {
    private int base;
    private int altura;
    private static int rectangulosCreados;

    public Rectangulo(int base, int altura){
        this.base = base;
        this.altura = altura;
        rectangulosCreados++;
    }

    public static int getRectangulosCreados() {
        return rectangulosCreados;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 1; i<= altura;i++){
            for(int j=1; j<= base; j++){
                res+="*";
            }
            res+="\n";
        }
        return res;
    }
}
