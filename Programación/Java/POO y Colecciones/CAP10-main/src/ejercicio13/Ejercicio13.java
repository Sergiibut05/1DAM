package ejercicio13;

public class Ejercicio13 {

    private static Almacen almacen = new Almacen(100);
    private static void menu(){
        System.out.println("""
            G E S T I S I M A L
            ===================
            1. Listado
            2. Alta
            3. Baja
            4. Modificación
            5. Entrada de mercancía
            6. Salida de mercancía
            7. Salir
            Introduzca una opción: 
                """);
    }

    private static void nuevo(){
        System.out.println("""
                NUEVO ARTÍCULO
                ==============
                Por favor, introduzca los datos del artículo.
                """);
        System.out.print("Descripción: ");
        String descripcion = System.console().readLine();
        System.out.print("Precio de compra: ");
        double precioCompra = Double.parseDouble(System.console().readLine());
        System.out.print("Precio de venta: ");
        double precioVenta = Double.parseDouble(System.console().readLine());
        Articulo a = new Articulo(descripcion, precioCompra, precioVenta);
        try {
            almacen.nuevoArticulo(a);
        } catch (ArticuloYaExisteException e) {
            System.out.println("El artículo ya existe");
        }
    }

    private static void listado(){
        System.out.println("""
                LISTADO
                ==============
                Por favor, introduzca los datos del artículo.
                """);
        System.out.println(almacen);
    }

    private static void baja(){
        System.out.println("""
                BAJA ARTÍCULO
                ==============
                """);
        System.out.print("Introduzca el código del artículo: ");
        String codigo = System.console().readLine();
        try {
            almacen.bajaArticulo(codigo);
        } catch (ArticuloNoExisteException e) {
            System.out.println("El artículo no existe");
        }
    }

    private static void modifica(){
        System.out.println("""
            MODIFICA ARTÍCULO
            ==============
            """);
        System.out.print("Introduzca el código del artículo: ");
        String cod = System.console().readLine();
        try {
            Articulo a = almacen.obtenerArticulo(cod);
            System.out.print("Descripción: ");
            System.out.println(a.getDescripcion());
            System.out.print("Nueva descripción: ");
            String descr = System.console().readLine();
            if(descr.isEmpty())
                descr = a.getDescripcion();
            System.out.print("Precio de compra: ");
            System.out.println(a.getPrecioCompra());
            System.out.print("Nuevo precio de compra: ");
            String _precioCompra = System.console().readLine();
            double precioCompra;
            if(_precioCompra.isEmpty())
                precioCompra = a.getPrecioCompra();
            else
                precioCompra = Double.parseDouble(_precioCompra);
            System.out.print("Precio de venta: ");
            System.out.println(a.getPrecioVenta());
            System.out.print("Nuevo precio de venta: ");
            String _precioVenta = System.console().readLine();
            double precioVenta;
            if(_precioVenta.isEmpty())
                precioVenta = a.getPrecioVenta();
            else
                precioVenta = Double.parseDouble(_precioVenta);
            Articulo nuevo = new Articulo(a.getCodigo(), descr, precioCompra, precioVenta);
            almacen.modificaArticulo(cod, nuevo);
        } catch (ArticuloNoExisteException e) {
            System.out.println("El artículo no existe");
        }
    }

    private static void entrada(){
        System.out.print("Por favor, introduzca el código del artículo: ");
        String codigo = System.console().readLine();
        System.out.println("Entrada de mercancía del siguiente artículo:");
        try {
            Articulo a = almacen.obtenerArticulo(codigo);
            System.out.print(a);
            System.out.print("Introduzca el número de unidades que entran: ");
            int unidades = Integer.parseInt(System.console().readLine());
            almacen.entrada(codigo, unidades);
            System.out.println("La mercancía ha entrado del almacen");
        } catch (ArticuloNoExisteException e) {
            System.out.println("No existe el artículo en el almacen");
        } catch (StockException e) {
            System.out.println("El stock facilitado es inválido");
        }
    }

    private static void salida(){
        System.out.print("Por favor, introduzca el código del artículo: ");
        String codigo = System.console().readLine();
        System.out.println("Salida de mercancía del siguiente artículo:");
        try {
            Articulo a = almacen.obtenerArticulo(codigo);
            System.out.print(a);
            System.out.print("Introduzca el número de unidades que salen: ");
            int unidades = Integer.parseInt(System.console().readLine());
            almacen.salida(codigo, unidades);
            System.out.println("La mercancía ha salido del almacen");
        } catch (ArticuloNoExisteException e) {
            System.out.println("No existe el artículo en el almacen");
        } catch (StockException e) {
            System.out.println("El stock facilitado es inválido");
        }
    }


    public static void main(String[] args) {
        int opcion = -1;

        do {
            menu();
            opcion = Integer.parseInt(System.console().readLine());    
            switch(opcion){
                case 1:
                    {
                      listado();      
                    }
                    break;
                case 2:
                    {
                        nuevo();
                    }
                    break;
                case 3:
                    {
                        baja();
                            
                    }
                    break;
                case 4:
                    {
                        modifica();       
                    }
                    break;
                case 5:
                    {
                       entrada();     
                    }
                    break;
                case 6:
                    {
                        salida();
                    }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 7);
    }
}
