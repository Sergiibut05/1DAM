package ejercicio7;

public class Moneda {
    private int valor;
    private String lado;

    public Moneda(){
        this.valor = switch((int)(Math.random()*8)){
            case 0->1;
            case 1->2;
            case 2->5;
            case 3->10;
            case 4->20;
            case 5->50;
            case 6->100;
            case 7->200;
            default->0;
        };
        this.lado = (Math.random()>0.5)?"cara":"cruz";
    }

    public Moneda(int valor, String lado){
        this.valor = valor;
        this.lado = lado;
    }

    public String getLado() {
        return lado;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object obj) {
        Moneda m = (Moneda)obj;
        return (m.getLado().equals(this.getLado()) ||
        m.getValor()==this.valor);
    }

    @Override
    public String toString() {
        return String.format("%d %s - %s",(valor>=100)?valor/100:valor,(valor>=100)?"euros":"céntimos", lado );
    }
}