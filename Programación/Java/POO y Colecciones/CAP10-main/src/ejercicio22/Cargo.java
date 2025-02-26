package ejercicio22;

public class Cargo extends Movimiento{
    public Cargo(double importe, double saldo){
        super("cargo", importe, saldo);
    }

    @Override
    public String toString() {
        return String.format("Cargo de %.2f € Saldo: %.2f €", importe, saldo);
    }
}
