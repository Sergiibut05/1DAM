package Calculo; 
public class Procesos {
    public static void ordenar(int num1){
        int resultado = 0;
        try {
            resultado = num1 % 10;  
            System.out.println("La última cifra del número introducido es el "+resultado);
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
