package ejercicio13;

import java.util.HashMap;
import java.util.Map;

public class Almacen implements StockInterface{

    HashMap<Articulo, Integer> stockArticulos;
    int stock;
    
    public Almacen(int articulos){
        stockArticulos = new HashMap<Articulo, Integer>();
        stock = 0; 
    }

    
    @Override
    public Articulo obtenerArticulo(String codigo){
        Articulo a = null;
        for(Map.Entry<Articulo, Integer> entry: stockArticulos.entrySet()){
            if(entry.getKey().getCodigo().equals(codigo)){
                a = entry.getKey();
                break;
            }
        }
        return a;
    }

    @Override
    public boolean existe(String codigo) {
        return obtenerArticulo(codigo)!=null;
    }

    @Override
    public String nuevoArticulo(Articulo a) throws ArticuloYaExisteException {
        if(stockArticulos.containsKey(a))
            throw new ArticuloYaExisteException();
        stockArticulos.put(a, 0);
        return a.codigo;
    }



    @Override
    public Articulo modificaArticulo(String codigo, Articulo a) throws ArticuloNoExisteException {
        Articulo anterior = obtenerArticulo(codigo);
        if(anterior==null)
            throw new ArticuloNoExisteException();
        int stockAnterior = stockArticulos.remove(anterior);
        stockArticulos.put(a, stockAnterior);
        return anterior;
    }



    @Override
    public Articulo bajaArticulo(String codigo) throws ArticuloNoExisteException {
        Articulo a = obtenerArticulo(codigo);
        if(a==null)
            throw new ArticuloNoExisteException();
        stockArticulos.remove(a);
        return a;
    }



    @Override
    public int entrada(String codigo, int cantidad) throws ArticuloNoExisteException, StockException{
        Articulo a = obtenerArticulo(codigo);
        if(a==null)
            throw new ArticuloNoExisteException();
        stockArticulos.put(a, stockArticulos.get(a)+cantidad);
        return stockArticulos.get(a);
    }



    @Override
    public int salida(String codigo, int cantidad) throws ArticuloNoExisteException, StockException {
        Articulo a = obtenerArticulo(codigo);
        if(a==null)
            throw new ArticuloNoExisteException();
        if((stockArticulos.get(a)-cantidad)<0)
            throw new StockException();
        stockArticulos.put(a, stockArticulos.get(a)-cantidad);
        return stockArticulos.get(a);
    }

    

    @Override
    public String toString() {
        String res = "";
        for(Map.Entry<Articulo,Integer> entry:stockArticulos.entrySet()){
            res += entry.getKey() + "\nStock: " + entry.getValue() + " artículos\n";
        }
        return res;
    }



    
}
