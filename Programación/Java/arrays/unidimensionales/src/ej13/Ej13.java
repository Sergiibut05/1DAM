package ej13;

public class Ej13 {
    public static int numAleatorio(){
        return (int)(Math.random() * 501);
    }
    public static void main(String[] args) {
        int[] numeros = new int[100];
        int max = 0;
        int min = 500;
        for(int i = 0; i <= 99; i++){
            numeros[i] = numAleatorio();
            if (numeros[i] > max) {
                max = Math.max(numeros[i], max);
            }else{
                min = Math.min(numeros[i], min);
            }
            System.out.print(numeros[i] + " ");
        }
        System.err.println("\n" + "¿Qué quiere destacar? (1 - mínimo, 2 - máximo): ");
        int operacion = Integer.parseInt(System.console().readLine());
        for(int i = 0; i <= 99; i++){
            if(operacion == 1 && numeros[i] == min) {
                System.out.printf("**%d** ", numeros[i]);
            }else if(operacion == 2 && numeros[i] == max){
                System.out.printf("**%d** ", numeros[i]);
            }else
            {
                System.out.printf("%d ", numeros[i]);
            }
        }
    }
}
