package ejercicio5_2bloque;

public class Ejercicio5 {
    private static Almacen almacen1 = new Almacen(5);

    private static void menu(){
        System.out.print("""
            G E S T I S I M A L
            ===================
            1. Listado
            2. Alta
            3. Baja
            4. Modificación
            5. Entrada de mercancía
            6. Salida de mercancía
            7. Salir
            Introduzca una opción: """);
    }

    private static void listar(){
        Articulo[] articulos = almacen1.getStockArticulos();

        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i] != null) {
                System.out.println(articulos[i]);
            }
        }
    }

    private static void altaArticulo(){
        System.out.print("""
                ALTA ARTICULO
                =============
                """);
        System.out.print("Inserte la descripción del nuevo artículo: ");
        String descripcion = System.console().readLine();
        System.out.print("Inserte el precio de compra del nuevo artículo: ");
        double precioCompra = Double.parseDouble(System.console().readLine());
        System.out.print("Inserte el precio de venta del nuevo artículo: ");
        double precioVenta = Double.parseDouble(System.console().readLine());

        try {
            almacen1.nuevoArticulo(new Articulo(descripcion, precioCompra, precioVenta));
        } catch (Exception e) {
        }
    }

    private static void bajaArticulo(){
        System.out.print("""
                BAJA ARTICULO
                =============""");
        System.out.print("Inserte el ID del artículo a eliminar: ");
        String codigo = System.console().readLine();

        try {
            almacen1.bajaArticulo(codigo);
        } catch (Exception e) {
        }
    }

    private static void entrarMercancia(){
        System.out.print("""
                ENTRADA DE MERCANCÍA
                ====================
                """);
        System.out.print("Inserte el ID del artículo a entrar: ");
        String id = System.console().readLine();
        System.out.print("Inserte la cantidad del artículo a entrar: ");
        int cantidad = Integer.parseInt(System.console().readLine());

        try {
            almacen1.entrada(id, cantidad);
        } catch (ArticuloNoExisteException e) {
            e.printStackTrace();
        } catch (StockException e) {
            e.printStackTrace();
        }
    }
    
    private static void sacarMercancia(){
        System.out.print("""
                SALIDA DE MERCANCÍA
                ===================
                """);
        System.out.print("Inserte el ID del artículo a sacar: ");
        String id = System.console().readLine();
        System.out.print("Inserte la cantidad del artículo a sacar: ");
        int cantidad = Integer.parseInt(System.console().readLine());

        try {
            almacen1.salida(id, cantidad);
        } catch (ArticuloNoExisteException e) {
            e.printStackTrace();
        } catch (StockException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            menu();
            opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 1:
                    listar();
                    break;

                case 2:
                    altaArticulo();
                    break;

                case 3:
                    bajaArticulo();
                    break;

                case 4:

                    break;

                case 5:
                    entrarMercancia();
                    break;

                case 6:
                    sacarMercancia();
                    break;

                default:
                    break;
            }
        } while (opcion != 7);
    }


}
