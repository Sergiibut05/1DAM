package ej11;

public class Ej11 {
    public static String nota(){
        String _nota = "" + (int)(Math.random()*5);
        switch (_nota) {
            case "0":
                _nota = "suspenso";
                break;
            case "1":
                _nota = "suficiente";
                break;
            case "2":
                _nota = "bien";
                break;
            case "3":
                _nota = "notable";
                break;
            case "4":
                _nota = "sobresaliente";
                break;

        
            default:
                break;
        }
        return _nota;
    }
    public static void main(String[] args) {
        int contsus= 0;
        int contsuf = 0;
        int contbie = 0;
        int contnot = 0;
        int contsob= 0;
        String _nota = "";
        for (int i = 1; i <= 20; i++){
            _nota = nota();
            System.out.printf("%s ", _nota);
            switch (_nota) {
                case "suspenso":
                    contsus++;
                    break;
                case "suficiente":
                    contsuf++;
                    break;
                case "bien":
                    contbie++;
                    break;
                case "notable":
                    contnot++;
                    break;
                case "sobresaliente":
                    contsob++;
            }
        }
        System.out.printf("Nº de suspensos: %d%n", contsus);
        System.out.printf("Nº de suficientes: %d%n", contsuf);
        System.out.printf("Nº de bienes: %d%n", contbie);
        System.out.printf("Nº de notables: %d%n", contnot);
        System.out.printf("Nº de sobresalientes: %d", contsob);
    }
}