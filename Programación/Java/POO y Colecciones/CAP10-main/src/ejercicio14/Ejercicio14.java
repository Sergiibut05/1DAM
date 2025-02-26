package ejercicio14;

import java.util.ArrayList;
import java.util.HashMap;

public class Ejercicio14 {
    static HashMap<String, Double> productos = new HashMap<>();
    private static void inicializaProductos(){
        productos.put("avena", 2.21);
        productos.put("garbanzos", 2.39);
        productos.put("tomate", 1.59);
        productos.put("jengibre", 3.13);
        productos.put("quinoa", 4.50);
        productos.put("guisantes", 1.60);
    }
    private static ArrayList<Pedido> solicitarCompra(){
        String producto = "";
        ArrayList<Pedido> compra = new ArrayList<>();
        do{
            System.out.print("Producto: ");
            producto = System.console().readLine();
            if(!producto.equals("fin"))
            {
                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(System.console().readLine());
                int index = -1;
                for (int i = 0; i < compra.size(); i++) {
                    if(compra.get(i).getProducto().equals(producto))
                    {
                        index = i;
                        break;
                    }
                }
                if(index!=-1)
                    compra.set(index, new Pedido(producto, compra.get(index).getCantidad()+cantidad));
                else
                    compra.add(new Pedido(producto, cantidad));
            }
        }while(!producto.equals("fin"));
        
        return compra;
    }

    private static void imprimirTicket(ArrayList<Pedido> compra){
        
        System.out.println("Producto\tPrecio\tCantidad\tSubtotal");
        System.out.println("--------\t------\t--------\t--------");
        double total = 0;
        for(Pedido pedido:compra){
            double subtotal = productos.get(pedido.getProducto())*pedido.getCantidad();
            System.out.printf("%-8s\t%.2f\t%d\t%.2f%n", 
                pedido.getProducto(), 
                productos.get(pedido.getProducto()), 
                pedido.getCantidad(), 
                subtotal);    
            total += subtotal;
        }
        System.out.println("--------\t------\t--------\t--------");
        System.out.printf("TOTAL: %.2f", total);
    }
    
    public static void main(String[] args) {
        inicializaProductos();
        ArrayList<Pedido> compra = solicitarCompra();
        imprimirTicket(compra);
    }
    
}
