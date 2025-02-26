import ejercicio5_2bloque.Almacen;
import ejercicio5_2bloque.Articulo;
import ejercicio5_2bloque.ArticuloNoExisteException;
import ejercicio5_2bloque.ArticuloYaExisteException;
import ejercicio5_2bloque.EspacioInsuficienteException;

public class ej5 {
    public Almacen almacen = new Almacen(10);

    private static void menu(){
        System.out.println("""
                GESTISIMAL
                ==========
                1. Listado
                2. Alta
                3. Baja
                4. Modificación
                5. Entrada de mercancía
                6. Salida de mercancía
                7. Salir""");
    }

    private void alta(Articulo a){

        System.out.print("Inserte la descripción: ");
        String descripcion = System.console().readLine();
        System.out.println();
        System.out.print("Inserte el precio de compra: ");
        int precioCompra = Integer.parseInt(System.console().readLine());
        System.out.println();
        System.out.print("Inserte el precio de venta: ");
        int precioVenta = Integer.parseInt(System.console().readLine());

        Articulo articulo = new Articulo(descripcion, precioCompra, precioVenta);
        try {
            almacen.nuevoArticulo(articulo);
        } catch (ArticuloYaExisteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EspacioInsuficienteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void baja(){
        System.out.print("Inserte el ID del artículo a eliminar: ");
        String id = System.console().readLine();
        try {
            almacen.bajaArticulo(id);
        } catch (ArticuloNoExisteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void listar(){
        for (int i = 0; i < almacen.length; i++) {
            
        }
    }



    public static void main(String[] args) {
        int opcion;

        do {
            menu();
            opcion = Integer.parseInt(System.console().readLine());
            switch (args) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }
        } while (opcion != 7);
    }
}
