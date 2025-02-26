package ejercicio22;

import java.util.ArrayList;

public class CuentaCorriente implements OperacionesCuentaCorriente{
    private double saldo;
    private long cc;

    ArrayList<Movimiento> movimientos = new ArrayList<>();

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
        movimientos.add(new Ingreso(importe, saldo));
    }

    @Override
    public void cargo(double importe) {
        this.saldo -= importe;
        movimientos.add(new Cargo(importe, saldo));
    }

    @Override
    public void transferencia(CuentaCorriente c, double importe) {
        this.saldo -= importe;
        movimientos.add(new Transferencia(-importe, saldo, c.cc));
        c.saldo += importe;
        c.movimientos.add(new Transferencia(importe, c.saldo, cc));
    }

    @Override
    public void movimientos() {
        for(Movimiento movimiento:movimientos){
            System.out.println(movimiento);
        }
    }

    
    
}
