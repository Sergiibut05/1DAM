package ejercicio11;

public class TarjetaRegalo implements TarjetaRegaloInterface{
    private double saldo;
    private int codigo;
    
    public TarjetaRegalo(double saldoInicial){
        saldo = saldoInicial;
        codigo = (int)(Math.random()*100000);
    }


    @Override
    public void gasta(double importe) {
        if(importe > saldo)
            System.out.printf("No tiene suficiente saldo para gastar %.2f€%n", importe);
        else
            saldo-=importe;
    }

    @Override
    public TarjetaRegalo fusionaCon(TarjetaRegalo t) {
        double otroSaldo = t.saldo;
        double miSaldo = saldo;
        t.saldo = 0;
        saldo = 0;
        return new TarjetaRegalo(miSaldo+otroSaldo);
    }

    @Override
    public String toString(){
        return String.format(
            "Tarjeta nº %05d - Saldo %.2f€",
            codigo, saldo);
    }
    
}
