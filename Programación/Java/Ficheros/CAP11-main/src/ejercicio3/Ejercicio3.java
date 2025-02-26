package ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio3 {
    public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("Error en el número de argumentos");
            return;
        }
        else{
            try {
                File orig1 = new File(args[0]);
                File orig2 = new File(args[1]);
                File dest = new File(args[2]);
                BufferedReader br1 = new BufferedReader(new FileReader(orig1));
                BufferedReader br2 = new BufferedReader(new FileReader(orig2));
                BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
                String linea1 = "";
                String linea2 = "";
                while(linea1 != null || linea2!=null){
                    linea1 = br1.readLine();
                    if(linea1!=null){
                        bw.write(linea1+"\n");
                    }
                    linea2 = br2.readLine();
                    if(linea2!=null){
                        bw.write(linea2+"\n");
                    }
                }
                br1.close();
                br2.close();
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
