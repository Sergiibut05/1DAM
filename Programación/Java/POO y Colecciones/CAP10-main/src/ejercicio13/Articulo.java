package ejercicio13;


/**
 * Clase Artículo
 * Almacena información útil de un artículo
 * Código (Alfanumérico aleatorio)
 * Descripción
 * precioCompra
 * precioVenta
 * stock (cantidad de artículo en el almacen)
 */
public class Articulo {
    String codigo;
    String descripcion;
    double precioCompra;
    double precioVenta;

    private static String obtenerNuevoCodigo(int longitud){
        String codigo = "";
        char minLetra = 'A';
        char maxLetra = 'Z';
        char minNumero = '0';
        char maxNumero = '9';
        for(int i = 1; i<=longitud; i++){
            if(Math.random()>0.5)
                codigo+=(char)(Math.random()*(maxLetra - minLetra - 1) + minLetra);
            else
                codigo+=(char)(Math.random()*(maxNumero - minNumero - 1) + minNumero);
        }
        return codigo;
    }

    public Articulo(String descripcion, double precioCompra, double precioVenta){
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.codigo = obtenerNuevoCodigo(3);
    }

    public Articulo(String codigo, String descripcion, double precioCompra, double precioVenta){
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }


    @Override
    public String toString() {
        return String.format("""
                ------------------------------------------
                Código: %s
                Descripción: %s
                Precio de compra: %.2f
                Precio de venta: %.2f
                """,codigo, descripcion, precioCompra, precioVenta);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Articulo(codigo, descripcion, precioCompra, precioVenta);
    }
}

