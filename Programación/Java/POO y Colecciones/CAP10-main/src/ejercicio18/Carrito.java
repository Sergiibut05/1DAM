package ejercicio18;

import java.util.ArrayList;
import java.util.HashMap;

public class Carrito implements CompraInterface{
    HashMap<String, Elemento> compra = new HashMap<>();

    public Carrito(){
        
    }

    @Override
    public int numeroDeElementos() {
        return compra.size();
    }

    @Override
    public double importeTotal() {
        double importe = 0;
        for(Elemento elemento:compra.values()){
            importe += elemento.getSubtotal();
        }
        return importe;
    }


    @Override
    public void agrega(Elemento elemento) {
        
        int cantidadAnterior = 0;
        if(compra.containsKey(elemento.getNombre()))
            cantidadAnterior = compra.get(elemento.getNombre()).getCantidad();
        compra.put(elemento.getNombre(), new Elemento(elemento.getNombre(), elemento.getPrecio(), cantidadAnterior+elemento.getCantidad()));
    }

    @Override
    public String toString() {
        String retorno = """
                Contenido del carrito
                =====================
                """;;
        for(Elemento elemento:compra.values())
            retorno += elemento.toString()+"\n";
        
        return retorno;
    }

    


    
}
