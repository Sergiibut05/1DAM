package Ejercicio65;

public class Ejercicio65 {
    public static void ejercicio65(){
        System.out.print("Introduzca la altura de la A (un número mayor o igual a 3): ");
        int altura = Integer.parseInt(System.console().readLine());
        if(altura<3){
            System.out.println("Error: Valor inadecuado");
        }else{
            System.out.printf("Introduzca la fila del palito horizontal (entre 2 y %d):",altura-1);
            int palito = Integer.parseInt(System.console().readLine());
            if(palito<2 || palito>=altura){
                System.out.println("Error: Valor inadecuado");
            }else{
                for(int i=1;i<=altura;i++){
                    for (int f=1;f<=altura-i;f++){
                        System.out.print(" ");
                    }
                    if(i==1){
                        System.out.print("*");
                    }else{
                        if(i==palito){
                            System.out.print("*");
                            for(int j=0;j<1+(2*(i-2));j++){
                                System.out.print("*");
                            }
                            System.out.print("*");
                        }else{
                            System.out.print("*");
                            for(int j=0;j<1+(2*(i-2));j++){
                                System.out.print(" ");
                            }
                            System.out.print("*");
                        }
                        
                    }
                    System.out.println();
                }
                
            }
        }   
    }
        
}
