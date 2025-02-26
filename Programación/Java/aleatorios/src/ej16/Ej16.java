package ej16;

public class Ej16 {
    public static String tirada(){
        String figura = "" + (int)(Math.random()*5);
        switch (figura) {
            case "0":
                figura = "corazón";
                break;
            case "1":
                figura = "diamante";
                break;
            case "2":
                figura = "herradura";
                break;
            case "3":
                figura = "campana";
                break;
            case "4":
                figura = "limon";
                break;
        
            default:
                break;
        }
        return figura;
    }
    public static void main(String[] args) {
        String figura1 = tirada();
        System.out.printf("%s ", figura1);
        String figura2 = tirada();
        System.out.printf("%s ", figura2);
        String figura3 = tirada();
        System.out.printf("%s%n", figura3);
        if (figura1.equals(figura2) && figura1.equals(figura3)&& figura2.equals(figura3)) {
            System.out.println("Enhorabuena, ha ganado 10 monedas");
        }else if (figura1.equals(figura2)|| figura1.equals(figura3)|| figura2.equals(figura3)){
            System.out.println("Bien, ha recuperado su moneda");
        }else{
            System.out.println("Lo siento, ha perdido");
        }
    }
}
