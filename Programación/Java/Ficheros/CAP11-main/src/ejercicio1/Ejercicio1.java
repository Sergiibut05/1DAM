package ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
    private static boolean esPrimo(int valor){
        boolean primo = true;
        for(int i = 2; i<=valor/2 && primo;i++){
            if(valor % i == 0)
                primo = false;
        }
        return primo;
    }
    public static void main(String[] args) {
        
        try {
            File file = new File("primos.dat");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            
            final int cantidad = 500;
            for(int i = 1; i<=cantidad; i++){
                if(esPrimo(i))
                    bw.write(i+"\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error");
        }
    }
    
}
