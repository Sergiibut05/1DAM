package ej3;

public class Ej3 {
    public static String numeroAlAzar(int min, int max){
        String num = null;
        num = "" + ((int)(Math.random()*(max-min+1))+min);
        return num;
    }

    public static void main(String[] args) {
        String num = numeroAlAzar(1, 10);
        String palo = numeroAlAzar(1, 4);
        switch (num) {
            case "1":
            num = "as";                
                break;
            case "8":
            num = "sota";                
                break;
            case "9":
            num = "caballo";                
                break;
            case "10":
            num = "rey";                
                break;
        
            default:
                break;
        }

        switch (palo) {
            case "1":
                palo = "copas";
                break;

            case "2":
            palo = "bastos";
                break;

            case "3":
            palo = "espadas";
                break;

            case "4":
            palo = "oros";
                break;
        
            default:
                break;
        }
        System.out.printf("%s de %s", num, palo);
    }
}
