package ejercicio7;

import java.util.ArrayList;

public class Ejercicio7 {
    public static void main(String[] args) {
        ArrayList<Moneda> monedas = new ArrayList<Moneda>();
        monedas.add(new Moneda());
        while(monedas.size()!=6){
            Moneda m = new Moneda();
            if(m.equals(monedas.get(monedas.size()-1))){
                monedas.add(m);
            }
        }
        System.out.print(monedas);
        
    }
    
}
