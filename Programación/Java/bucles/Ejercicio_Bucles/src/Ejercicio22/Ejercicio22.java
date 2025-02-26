package Ejercicio22;

public class Ejercicio22 {
    public static void ejercicio22(){
    int n= 2; 
    int x=2; 
    boolean esprimo = false;  
        do{
            esprimo=false;
            x=2;
            do{
                if (n%x==0) {
                    x++;
                } else{
                    System.out.println(n);
                    esprimo=true;
                }  
            }while (esprimo=false & x<(n/2)+1);
            n++;
        } while (n<100);
    }
}
