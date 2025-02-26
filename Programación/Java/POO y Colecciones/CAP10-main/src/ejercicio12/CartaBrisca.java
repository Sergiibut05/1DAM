package ejercicio12;

import ejercicio8.Carta;

public class CartaBrisca extends Carta{

    public CartaBrisca(int i) {
        super(i);
    }


    public int getValor(){
        return switch(getNumero(getCodigo())){
            case "as"->11;
            case "tres"->10;
            case "sota"->2;
            case "caballo"->3;
            case "rey"->4;
            default->0;
            
        };
    }
    
}
