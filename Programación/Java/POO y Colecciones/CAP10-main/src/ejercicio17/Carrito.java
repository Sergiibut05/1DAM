package ejercicio17;

import java.util.ArrayList;

public class Carrito implements CompraInterface{
    ArrayList<Elemento> compra = new ArrayList<>();

    public Carrito(){
        
    }

    @Override
    public int numeroDeElementos() {
        return compra.size();
    }

    @Override
    public double importeTotal() {
        double importe = 0;
        for(Elemento elemento:compra){
            importe += elemento.getSubtotal();
        }
        return importe;
    }


    @Override
    public void agrega(Elemento elemento) {
        compra.add(elemento);
    }

    @Override
    public String toString() {
        String retorno = """
                Contenido del carrito
                =====================
                """;;
        for(Elemento elemento:compra)
            retorno += elemento.toString()+"\n";
        
        return retorno;
    }

    


    
}
