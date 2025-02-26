package ejercicio13;

public class CuentaCorriente implements OperacionesCuentaCorriente{
    private double saldo;
    private long cc;

    public CuentaCorriente(){
        this.saldo = 0;
        this.cc = (long)(Math.random()*10000000000l);
    }

    public CuentaCorriente(double saldo){
        this();
        this.saldo = saldo;
    }

    @Override
    public String toString(){
        return String.format(
            "Número de cta: %010d Saldo: %.2f €",
            cc, saldo);
    }

    @Override
    public void ingreso(double importe) {
        this.saldo += importe;
    }

    @Override
    public void cargo(double importe) {
        this.saldo -= importe;
    }

    @Override
    public void transferencia(CuentaCorriente c, double importe) {
        this.saldo -= importe;
        c.saldo += importe;
    }
    
}
