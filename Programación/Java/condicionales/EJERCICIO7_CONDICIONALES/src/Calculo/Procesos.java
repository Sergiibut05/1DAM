package Calculo; 
public class Procesos {
    public static void ecsegundogrado(Double a,Double b,Double c){
        Double numero = (b*b)-(4*a*c);
        Double Raiz = Math.sqrt(numero);
        Double result1 =( -b + Raiz)/(2*a);
        Double result2 =( -b - Raiz)/(2*a);
        try {
            if (a!=0 & b!=0 & c!=0){
                System.out.println("x1 = "+result1);
                System.out.println("x2 = "+result2);
            }
            if (a==0 & b==0 & c==0){
                System.out.println("La ecuación tiene infinitas soluciones.");
            }
            if (a==0 & b==0 & c!=0){
                System.out.println("La ecuación no tiene solución");
            }
            if (a>0 & b>0 & c>0){
                System.out.println("La ecuación no tiene soluciones reales");
            }
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
