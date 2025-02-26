package ejercicio22;

public class Transferencia extends Movimiento{
    long cuenta;

    public Transferencia(double importe, double saldo, long cuenta){
        super("transferencia", importe, saldo);
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return String.format(
            "Transf. %s de %.2f € %s la cuenta %d Saldo %.2f", 
            ((importe<0)?"emitida":"recibida"), 
            Math.abs(importe), 
            ((importe<0)?"a":"de"),
            cuenta,
            saldo);
    }
    
}
