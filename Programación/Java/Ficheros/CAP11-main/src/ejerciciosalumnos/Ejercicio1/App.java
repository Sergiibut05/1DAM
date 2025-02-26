package ejerciciosalumnos.Ejercicio1;

import java.io.IOException;

public class App {
    
    private static GestorFlamenco gf;
    private static void menu(){
        System.out.println("1. Listar frases");
        System.out.println("2. Añadir una frase");
        System.out.println("3. Eliminar frase");
        System.out.println("4. Frase por ID");
        System.out.println("5. Guardar en Archivo");
        System.out.println("6. Eliminar archivo de frases");
        System.out.println("7. Salir");
    }
    public static void main(String[] args) {
        try {
            if(args.length != 1){
                System.out.println("Error en los argumentos de entrada");
                return;
            }
            gf = new GestorFlamenco(args[0]);
            int opcion = -1;
            do {
                menu();
                opcion = Integer.parseInt(System.console().readLine());
                switch (opcion) {
                    case 1:
                    {
                        for(FraseFlamenca frase:gf.listar())
                            System.out.println(frase);
                    }   
                    break;
                    case 2:
                    {
                        System.out.println("Introduzca una frase flamenca");
                        String frase = System.console().readLine();
                        FraseFlamenca fraseFlamenca = gf.anadeFrase(frase);
                        System.out.println("Frase añadida");
                        System.out.println(fraseFlamenca);
                    } 
                    break;
                    case 3:
                    {
                        System.out.println("Intoduzca el id de la frase");
                        Integer id = Integer.parseInt(System.console().readLine());
                        if(gf.eliminaFrase(id))
                            System.out.println("Frase eliminada corréctamente");
                        else
                            System.out.println("No se ha podido eliminar la frase");
                    }
                    break;
                    case 4:
                    {
                        System.out.println("Intoduzca el id de la frase");
                        Integer id = Integer.parseInt(System.console().readLine());
                        FraseFlamenca fraseFlamenca = gf.obtenerPorID(id);
                        if(fraseFlamenca!=null)
                            System.out.println(fraseFlamenca);
                        else
                            System.out.println("No se ha podido obtener la frase");
                    }
                    case 5:
                    {
                        if(gf.guardar())
                            System.out.println("Se guardó corréctamente la información en el archivo");
                        else
                            System.out.println("Ocurrió un error al guardar");
                    }
                    break;
                    case 6:
                    {
                        if(gf.borrarFichero())
                            System.out.println("Se eliminó el archivo");
                        else
                            System.out.println("Ocurrió un error al eliminar el archivo");
                    }
                    break;
                    default:
                        break;
                }
            } while (opcion != 7);
        } catch (IOException e) {
            
        }
        
    }
}
