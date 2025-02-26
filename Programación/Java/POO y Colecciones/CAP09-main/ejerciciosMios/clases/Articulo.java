package clases;

public class Articulo {
    private String codigo;
    private String descripcion;
    private int precioCompra;
    private int precioVenta;
    private int stock;

    public Articulo(String descripcion, int precioCompra, int precioVenta){
        codigo = generarCodigo();
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        stock = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }
    
    public int getPrecioVenta() {
        return precioVenta;
    }

    public int getStock() {
        return stock;
    }

    private String generarCodigo(){
        String codigo = "";
        int longCod = 3;
        char maxNum = '9';
        char minNum = '0';
        char maxLet = 'Z';
        char minLet = 'A';

        for (int i = 0; i < longCod; i++) {
            if (Math.random() < 0.5) {
                codigo += (char)(Math.random() * (maxLet - minLet + 1) - minLet);
            }else{
                codigo += (char)(Math.random() * (maxNum - minNum + 1) - minNum);
            }
        }
        return codigo;
    }

    @Override
    public String toString() {
        return String.format("""
                Código: %s
                Descripcion: %s
                Precio de compra: %.2f
                Precio de venta: %.2f
                Stock: %d unidades
                """, codigo, descripcion, precioCompra, precioVenta, stock);
    }
}
