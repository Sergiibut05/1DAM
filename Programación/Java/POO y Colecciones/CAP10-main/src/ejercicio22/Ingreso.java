package ejercicio22;

public class Ingreso extends Movimiento{
    public Ingreso(double importe, double saldo){
        super("ingreso", importe, saldo);
    }

    @Override
    public String toString() {
        return String.format("Ingreso de %.2f € Saldo: %.2f €", importe, saldo);
    }
}
