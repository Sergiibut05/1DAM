package introduccion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    private static void leer1(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("numeros.txt"));
            double suma = 0;
            int cantidad = 0;
            String linea = "";
            while((linea=br.readLine())!=null){
                try {
                    suma += Double.parseDouble(linea);
                    cantidad++;    
                } catch (Exception e) {
                    
                }
            }
            br.close();
            System.out.printf("La media de los números es %.2f",suma/cantidad);
        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo numeros.txt");
        } catch (IOException e) {
            System.out.println("Se ha producido un error al leer desde el fichero");
        }
    }

    private static void escribir1(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("fruta.txt", true));
            bw.write("naranja\t");
            bw.flush();
            bw.write("mango\t");
            bw.flush();
            bw.write("chirimoya\t");
            
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo");
        }
        
    }

    private static void leerYEscribir(){
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("fichero1.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("fichero2.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("mezcla.txt"));
            String linea1="", linea2="";
            while(linea1 != null || linea2!=null){
                if((linea1=br1.readLine())!=null){
                    bw.write(linea1+"\n");
                }
                if((linea2=br2.readLine())!=null){
                    bw.write(linea2+"\n");
                }
            }
            br1.close();
            br2.close();
            bw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        

    }
    private static void listar(String path){
        File file = new File(path);
        String[] listado = file.list();
        for(String archivo:listado){
            System.out.println(archivo);
        }
    }

    private static void delete(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
            System.out.println("Se ha eliminado el fichero");
        }
        else{
            System.out.println("El fichero no existe");
        }
    }

    private static void changeTabs(String fileName){
        File fileOriginal = new File(fileName);
        File fileTemporal = new File(fileName+".tmp");
        fileOriginal.renameTo(fileTemporal);
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(fileTemporal));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOriginal));
            String linea = "";
            while((linea = br.readLine()) != null){
                
                bw.write(linea.replace("\t", "    ")+"\n");
            }
            br.close();
            bw.close();
            fileTemporal.delete();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch( IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        
        if(args.length == 1){
            String fileName = args[0];
            changeTabs(fileName);
        }
        else{
            System.out.println("Error en el paso de argumentos");
        }
    }
    
}
