package Ejercicio26;
public class Ejercicio26 {
    public static void ejercicio26(){
        
        System.out.printf("Introduzca un número entero: ");
        int n = Integer.parseInt(System.console().readLine());
        System.out.printf("Introduzca un dígito: ");
        int d = Integer.parseInt(System.console().readLine());
        int contador = (int)Math.log10(n)+1;
        System.out.printf("Contando de izquierda a derecha, el %d aparece dentro de %d en las siguientes posiciones: ",d,n);
        int n1=0;
        int i=1;
        while (n!=0){
            n1=(int)n/(int)(Math.pow(10, contador-1));
            if (n1==d){
                System.out.printf(" %d",i);
            }
            n=n-(n1*(int)(Math.pow(10, contador-1)));
            contador=contador-1;
            i=i+1;
        }

    }
}
