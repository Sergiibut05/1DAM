package ej22;

public class Ej22 {
    public static int posicion(int desplazamiento){
        desplazamiento = desplazamiento += (int)((Math.random()*4)-2);
        return desplazamiento;
    }
    public static void main(String[] args) {
        System.out.print("Por favor, introduzca la longitud de la serpiente contando la cabeza: ");
        int longitud = Integer.parseInt(System.console().readLine());
        int desplazamiento = 12;
        for (int i = 1; i <= longitud;i++){
            desplazamiento = posicion(desplazamiento);
            for (int j = 1; j <= desplazamiento; j++){
            System.out.print(" ");
            
        }
        if (i == 1) {
            System.out.println("@");
        }else{
            System.out.println("#");
        }
        }

    }
}
