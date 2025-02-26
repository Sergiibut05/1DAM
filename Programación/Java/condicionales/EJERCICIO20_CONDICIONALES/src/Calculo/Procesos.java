package Calculo; 
public class Procesos {
    public static void tiempoviernes(int a,int b,int d, String c){
        if (d<6){
            if (d==5){
                if (a<16){
                    if (a == 15){
                        if (b==0){
                            delunesaviernes(a, b, d, c);
                        }
                        else {
                            apartirdelviernes(a, b, d, c);
                        }
                    }
                    else{
                        delunesaviernes(a, b, d, c);
                    }
                }
                else {
                    apartirdelviernes(a, b, d, c);
                }
            }
            else {
                delunesaviernes(a, b, d, c);
            }
                
            

        }
        else {
            apartirdelviernes(a, b, d, c);
        }
    }
    public static int diasemana(String c){
        int rest = 0;
        switch (c.toUpperCase()){
            case"LUNES":
                rest=1;
                break;
            case"MARTES":
                rest=2;
                break;
            case"MIERCOLES":
                rest=3;
                break;
            case"JUEVES":
                rest=4;
                break;
            case"VIERNES":
                rest=5;
                break;
            case"SABADO":
                rest=6;
                break;
            case"DOMINGO":
                rest=7;
                break;
        }
        return rest;
    }
    public static void apartirdelviernes(int a,int b,int d, String c){
        int a1 = a*60;
        int b1 = b;
        int resultado = (7*24*60)-(d*(a1+b1));
        resultado = resultado +(4*24*60);
        System.out.println("Faltan "+resultado+" minutos para llegar al fin de semana.");
    }
    public static void delunesaviernes(int a,int b,int d, String c){
        int a1 = a*60;
        int b1 = b;
        int resultado = ((4*24*60)+(15*60))-(((d-1)*24*60)+(a1+b1));
        System.out.println("Faltan "+resultado+" minutos para llegar al fin de semana.");
    }
    
}
