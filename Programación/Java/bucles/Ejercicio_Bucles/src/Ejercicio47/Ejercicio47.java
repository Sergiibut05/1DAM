package Ejercicio47;

public class Ejercicio47 {
    public static void ejercicio47(){
        System.out.print("Por favor, introduzca la altura (número impar mayor o igual a 5): ");
        int altura = Integer.parseInt(System.console().readLine());
        if(altura>=5 && altura%2!=0){
            for(int i=1;i<=altura;i++){
                if(i==altura || i==1 || i==altura/2+1){
                    System.out.println("MMMMMM");
                }else{
                    System.out.println("M    M");
                }
            }
        }else{
            System.out.println("La altura introducida no es correcta.");
        }
        
    }
}
