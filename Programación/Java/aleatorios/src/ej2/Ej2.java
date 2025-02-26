package ej2;

public class Ej2 {
    public static String numeroAlAzar(int min, int max){
        String num = null;
        num = "" + ((int)(Math.random()*(max-min+1))+min);
        return num;
    }

    public static void main(String[] args){
        String num = numeroAlAzar(1, 13);
        String palo = numeroAlAzar(1, 4);
        switch (num) {
            case "1":
                num = "A";
                break;
            case "11":
                num = "J";
                break;
            case "12":
                num = "Q";
                break;
            case "13":
                num ="K";
                break;
        
            default:
                break;
        }
        
        switch (palo) {
            case "1":
                palo = "picas";
                break;
            case "2":
            palo = "corazones";
            break;
            case "3":
            palo = "diamantes";
            break;
            case "4":
            palo = "tréboles";
            break;
        
            default:
                break;
        }
        System.out.printf("%s de %s", num, palo);
    }
}
