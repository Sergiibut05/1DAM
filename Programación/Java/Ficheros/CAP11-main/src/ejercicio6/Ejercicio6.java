package ejercicio6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Ejercicio6 {
        private static ArrayList<Character> caracteresEspanolCompleto = new ArrayList<>(Arrays.asList(
            // Mayúsculas sin acento
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            
            // Mayúsculas acentuadas y con diéresis
            'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü',
            
            // Minúsculas sin acento
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            
            // Minúsculas acentuadas y con diéresis
            'á', 'é', 'í', 'ó', 'ú', 'ü',
            
            // Dígitos
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        ));
    private static boolean esPermitida(char caracter){
        return caracteresEspanolCompleto.contains(caracter);
    }
    public static void main(String[] args) {
        ArrayList<String> palabras = new ArrayList<>();
        HashMap<String, Integer> palabras2 = new HashMap<>();
        if(args.length != 2){ 
            System.out.println("Error en el número de argumentos");
            return;
        }
        else{
            try {
                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                String aBuscar = args[1];
                String linea = "";
                String enUnaLinea = "";
                while((linea=br.readLine())!=null){
                    enUnaLinea += linea;
                }
                br.close();
                boolean enPalabra = true;
                String palabraActual = "";
                for(int i=0; i < enUnaLinea.length(); i++){
                    if(esPermitida(enUnaLinea.charAt(i))){
                        palabraActual+=enUnaLinea.charAt(i);
                        enPalabra = true;
                    }
                    else{
                        if(enPalabra){
                            palabras.add(palabraActual);
                            int contador = 1;
                            if(palabras2.containsKey(palabraActual)){
                                contador = palabras2.get(palabraActual) + contador;
                            }
                            palabras2.put(palabraActual, contador);
                        }
                        enPalabra = false;
                        palabraActual = "";
                    }
                }
                int cont = 0;
                int index = -1;
                while((index = palabras.indexOf(aBuscar))!=-1){
                    palabras.remove(index);
                    cont++;
                }
                System.out.println(aBuscar+ " aparece "+cont+ " veces en el archivo: "+args[0]);
                System.out.println(aBuscar+ " aparece "+palabras2.get(aBuscar)+ " veces en el archivo: "+args[0]);
                

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
