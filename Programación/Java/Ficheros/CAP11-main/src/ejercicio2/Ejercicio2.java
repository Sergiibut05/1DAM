package ejercicio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {

        
        try {
            File file = new File("primos.dat");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea = "";
            while((linea=br.readLine())!=null){
                System.out.println(linea);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocurrión un error al leer el archivo. No existe");
        } catch (IOException e){
            System.out.printf("Ocurrión un error al leer el archivo. %s", e.getMessage());
        }
    }
}
