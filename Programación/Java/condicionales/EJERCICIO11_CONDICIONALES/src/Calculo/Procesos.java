package Calculo; 
public class Procesos {
    public static void ecsegundogrado(int a,int b){
        int a1 = a*60*60;
        int b1 = b*60;
        int resultado = (24*60*60)-(a1+b1);
        try {
            System.out.println("Desde las "+a+":"+b+" hasta la medianoche faltan "+resultado+" segundos.");
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
