/*package ej32;

public class Ej32 {
    public static int totalDesplazamiento(int desplazamiento){
        desplazamiento = (int)((Math.random()*3)-1);
        return desplazamiento;
    }

    public static int obstaculoONo(){
        return (int)Math.random()*2;
    }

    public int posi

    public static int obstaculo(){
        char _obstaculo = (char)(Math.random()*3);
        if (obstaculoONo() == 1) {
            switch (_obstaculo) {
                case '0':
                    _obstaculo = '*';
                    break;
    
                case '1':
                    _obstaculo = '0';
            
                default:
                    break;
            }
        }
        return _obstaculo;
    }    
    public static void main(String[] args) {
        System.out.print("Introduzca la distancia en metros: ");
        int longitud = Integer.parseInt(System.console().readLine());
        int desplazamiento = 5;
        for (int i = 1; i <= longitud; i++){
            desplazamiento += totalDesplazamiento(desplazamiento);
            for (int j = 1; j < desplazamiento; j++){
                System.out.print(" ");
            }
            System.out.printf("| ", args)();
        }
    }
}*/

//AYUDA NO SE DEVOLVER DOS VARIABLES