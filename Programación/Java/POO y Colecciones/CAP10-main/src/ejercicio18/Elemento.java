package ejercicio18;

public class Elemento{
    private String nombre;
    private double precio;
    private int cantidad;
    public Elemento(String nombre, double precio, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }


    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }


    public double getSubtotal(){
        return precio * cantidad;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || this.getClass().equals(obj.getClass()) && this.nombre.equals(((Elemento)obj).nombre);
    }
    
    @Override
    public String toString() {
        return String.format(
            "%s PVP: %.2f Unidades: %d Subtotal: %.2f",
            nombre, precio, cantidad, precio*cantidad);
    }
    
}
