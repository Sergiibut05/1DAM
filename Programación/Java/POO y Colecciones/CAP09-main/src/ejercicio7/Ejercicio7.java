package ejercicio7;

public class Ejercicio7 {

    private static void submenu2(){
        System.out.println("1. Principal");
        System.out.println("2. Compra-venta");
        System.out.println("3. Vip");
    }

    private static void menu(){
        System.out.println("1. Mostrar número de entradas libres");
        System.out.println("2. Vender entradas");
        System.out.println("3. Salir");
    }
    public static void main(String[] args) {
        Zona z1 = new Zona(1000);
        Zona z2 = new Zona(200);
        Zona z3 = new Zona(25);
        int opcion = 0;
        do {
            menu();
            opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 1:
                    System.out.println("En la zona principal hay "+ z1.getEntradasPorVender() +" libres");
                    System.out.println("En la zona de compra venta hay "+ z2.getEntradasPorVender() +" libres");
                    System.out.println("En la zona vip hay "+ z3.getEntradasPorVender() +" libres");
                    break;
                case 2:
                    submenu2();
                    int _opcion = Integer.parseInt(System.console().readLine());
                    System.out.print("¿Cuántas entradas quieres?");
                    int entradas = Integer.parseInt(System.console().readLine());
                    switch (_opcion) {
                        case 1:
                            z1.vender(entradas);
                            break;
                        case 2:
                            z2.vender(entradas);
                            break;
                        case 3:
                            z3.vender(entradas);
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (opcion != 3);
    }


    
}
