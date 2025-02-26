package ejercicio14;

public class Pedido {
    String producto;
    int cantidad;
    public Pedido(String producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getProducto() {
        return producto;
    }
    
}
