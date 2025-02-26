package ejercicio22;

public interface OperacionesCuentaCorriente {
    public void ingreso(double importe);
    public void cargo(double importe);
    public void transferencia(CuentaCorriente c, double importe);
    public void movimientos();
}
