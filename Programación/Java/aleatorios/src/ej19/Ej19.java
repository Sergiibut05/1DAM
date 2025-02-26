package ej19;

public class Ej19 {
    public static int capacidadAgua(int altura){
        int agua = (int)(Math.random()*(altura-1));
        return agua;
    }
    public static void main(String[] args) {
        System.out.print("Por favor, indique la capacidad en litros: ");
        int capacidad = Integer.parseInt(System.console().readLine());
        int agua = capacidadAgua(capacidad-1);
        for (int i = 1; i <= capacidad; i++){
            if (i == capacidad){
                System.out.println("######");
            }else if (i > capacidad - agua){
                System.out.println("#====#");
            }else{
                System.out.println("#    #");

            }
        }
    }
}
