package ejercicio4;

public class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador){
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    public Fraccion multiplica(int numero){
        return new Fraccion(numero*this.numerador, this.denominador);
    }

    public Fraccion multiplica(Fraccion f){
        return new Fraccion(this.numerador*f.numerador, this.denominador*f.denominador);
    }

    public Fraccion divide(int numero){
        return new Fraccion(this.numerador, this.denominador*numero);
    }

    public Fraccion divide(Fraccion f){
        return new Fraccion(this.numerador*f.denominador, this.denominador*f.numerador);
    }

    public Fraccion invierte(){
        return new Fraccion(this.denominador, this.numerador);
    }

    public Fraccion potencia(int exponente){
        if(exponente==0)
            return new Fraccion(1,1);
        else if(exponente<0){
            return invierte().potencia(-exponente);
        }
        else{
            return new Fraccion(this.numerador*this.numerador, this.denominador*this.denominador);
        }
    }

    public Fraccion simplifica(){
        int _numerador = numerador;
        int _denominador = denominador;
        int min = Math.min(Math.abs(_numerador), Math.abs(_denominador));
        int cont = 2;
        while(cont <= min){
            if(_numerador%cont==0 && _denominador%cont==0){
                _numerador/=cont;
                _denominador/=cont;
                min = Math.min(Math.abs(_numerador), Math.abs(_denominador));
            }
            else
                cont++;
        }
        return new Fraccion(_numerador, _denominador);
    }

    @Override
    public String toString(){
        return String.format(
            "%s%d/%d", 
            (this.numerador*this.denominador)<0?"-":"",
            Math.abs(this.numerador), 
            Math.abs(this.denominador));
    }
    
}
