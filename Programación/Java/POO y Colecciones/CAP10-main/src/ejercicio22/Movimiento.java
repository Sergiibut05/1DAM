package ejercicio22;

import java.util.Date;

public class Movimiento {
    String tipo; //ingreso, cargo, transferencia
    double importe;
    double saldo;
    Date fecha;

    

    public Movimiento(String tipo, double importe, double saldo){
        this.tipo = tipo;
        this.saldo = saldo;
        this.importe = importe;
        this.fecha = new Date();
    }
    
}
