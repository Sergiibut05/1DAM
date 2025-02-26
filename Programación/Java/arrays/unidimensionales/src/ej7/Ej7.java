package ej7;

public class Ej7 {
    public static int numAleatorio(){
        return (int)(Math.random()*21);
    }
    public static void main(String[] args) {
        int numerosGenerados = 100;
        int[] numeros = new int[numerosGenerados];
        for (int i = 0; i <= numerosGenerados-1; i++){
            numeros[i] = numAleatorio();
            System.out.printf("%d ", numeros[i]);
        }
        System.out.println();
        System.out.print("Introduzca un número de los valores que se le han mostrado: ");
        int numeroASustituir = Integer.parseInt(System.console().readLine());
        System.out.print("Introduzca el valor por el que quiere sustituirlo: ");
        int numeroSustituto = Integer.parseInt(System.console().readLine());
        for (int i = 0; i <= numerosGenerados-1; i++){
            if (numeros[i] == numeroASustituir) {
                numeros[i] = numeroSustituto;
                System.out.printf("\"%d\" ", numeros[i]);
            }else{
                System.out.printf("%d ", numeros[i]);
            }
        }

    }
}
