import ejercicio5_2bloque.Articulo;

public class Almacen {
    private Articulo[] stockProducto;
    int stock;

    public Almacen(int articulos){
        stockProducto = new Articulo(articulos);
        stock = 0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private int encontrarHuecoLibre(){
        int hueco = -1;
        int posicion = 0;

        while (hueco == -1 && posicion <= stockProducto.length) {
            if (stockProducto[posicion] == null) {
                hueco = posicion;
            }
            posicion++;
        }
        return hueco;
    }

    private int encontrarProducto(String id){
        int hueco = -1;
        int posicion = 0;

        while (hueco == -1 && posicion <= stockProducto.length) {
            if (stockProducto[posicion] == id) {
                hueco = posicion;
            }
            posicion++;
        }
        return hueco;
    }

    public String darAlta(Articulo a){
        int indice = encontrarHuecoLibre();
        if (indice == -1) {
            return false;
        }else{
            stockProducto[indice] = a;
        }

        return a.getCodigo();
    }

    public void darBaja(String codigo){
        int indice = encontrarHuecoLibre();
        if (indice == -1) {
            System.out.println("No se ha encontrado el articulo");;
        }
        stockProducto[indice] = null;
    }

    public void entradaMercancia(String codigo, int cantidad){
        int posicion = encontrarProducto(codigo);

        stockProducto[posicion].getStock(stockProducto[posicion].getStock + cantidad);
    }

    public void salidaMercancia(String codigo, int cantidad){
        int posicion = encontrarProducto(codigo);

        stockProducto[posicion].getStock(stockProducto[posicion].getStock - cantidad);
    }


}
