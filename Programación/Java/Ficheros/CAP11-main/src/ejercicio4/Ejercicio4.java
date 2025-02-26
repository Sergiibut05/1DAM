package ejercicio4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio4 {
    public static void main(String[] args) {
        
        if(args.length != 1){ 
            System.out.println("Error en el número de argumentos");
            return;
        }
        else{
            try {
                ArrayList<String> palabras = new ArrayList<>();
                File origen = new File(args[0]);
                File destino = new File(origen+".sort");
                BufferedReader br = new BufferedReader(new FileReader(origen));
                
                String linea = "";
                while((linea = br.readLine()) !=null){
                    palabras.add(linea);
                }
                br.close();
                Collections.sort(palabras);
                BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
                for(String palabra:palabras){
                    bw.write(palabra+"\n");
                }
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
