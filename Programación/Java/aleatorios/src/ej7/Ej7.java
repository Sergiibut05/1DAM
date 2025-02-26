package ej7;

public class Ej7 {
    public static String apuesta(){
        String _apuesta = "" + (int)((Math.random()*3)+1);
        switch (_apuesta) {
            case "1":
                _apuesta = "1  ";
                break;
            case "2":
                _apuesta = " X ";
                break;
            case "3":
                _apuesta = "  2";
                break;
            default:
                break;
        }
        return _apuesta;
    }

    public static String pleno(){
        String _apuesta = "" + (int)((Math.random()*4));
        switch (_apuesta) {
            case "3":
                _apuesta = "M";
                break;
            default:
                break;
        }
        return _apuesta;
    }
    public static void main(String[] args) {
        for (int i = 1; i <= 14; i++){
            System.out.printf("%2d. |%s|%s|%s|%n", i, apuesta(), apuesta(), apuesta());
        }
        System.out.printf("%nPLENO AL 15 - LOCAL...%s   VISITANTE...%s", pleno(), pleno());
    }
}
