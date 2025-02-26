package ejercicio5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio5 {
    public static void main(String[] args) {
        if(args.length != 1){ 
            System.out.println("Error en el número de argumentos");
            return;
        }
        else{
            try {                 
                File origen = new File(args[0]);
                File temporal = new File(args[0]+".tmp");
                origen.renameTo(temporal);
                
                BufferedReader br = new BufferedReader(new FileReader(temporal));
                
                String linea = "";
                String enUnaLinea = "";
                while((linea = br.readLine()) !=null){
                    enUnaLinea += linea;
                }

                String resultado = "";
                boolean saltando1 = true;
                boolean saltando2 = true;
                for(int i=0; i < enUnaLinea.length();i++){
                    if(enUnaLinea.charAt(i)=='/'){
                        if(enUnaLinea.charAt(i+1)=='/'){
                            saltando1 = true;
                            i++;
                        }
                        else if(enUnaLinea.charAt(i+1)=='*'){
                            saltando2 = true;
                            i++;
                        }
                        else
                            resultado += enUnaLinea.charAt(i);
                    }
                    else if(enUnaLinea.charAt(i)=='\n' && saltando1)
                        saltando1 = false;
                    else if(enUnaLinea.charAt(i)=='*' && enUnaLinea.charAt(i+1)=='/' && saltando2){
                        i++;
                        saltando2 = false;
                    }
                    else
                        resultado += enUnaLinea.charAt(i);
                }
                br.close();
                BufferedWriter bw = new BufferedWriter(new FileWriter(origen));
                bw.write(resultado);
                bw.close();
                temporal.delete();
            } catch (IOException e) {
                                e.printStackTrace();
            }
        }
    }
}
