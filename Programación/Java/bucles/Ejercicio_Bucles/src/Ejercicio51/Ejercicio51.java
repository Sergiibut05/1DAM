package Ejercicio51;

public class Ejercicio51 {
    public static void ejercicio51(){
        System.out.print("Introduzca un número entero (mayor que cero): ");
        int num = Integer.parseInt(System.console().readLine());
        
        int longitud = (int)Math.log10(num)+1;
        int valor=0;
        int num1=num;
        int part1=0;
        int part2=0;
        boolean cambio=false;
        for(int i=1; i<=longitud;i++){
            valor=num1%10;
            if(valor==0 || valor==8){
                longitud=longitud-1;
                part1=num/(int)Math.pow(10, i);
                part2=num%(int)Math.pow(10, i-1);
                i=i-1;
                num=(int)Math.pow(10, i)*part1+part2;
                num1=part1;
                cambio = true;
            }else{
                num1=num1/10;
            }
            
        }
        if(cambio=true){
            System.out.print("Después de haber sido comido por el gusano numérico se queda en "+num);
        }else{
            System.out.println("El gusano numérico no se ha comido ningún dígito.");
        }
        
    }
}
