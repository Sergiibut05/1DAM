package ej15;

public class Ej15 {
    public static String nota(){
        String _nota = "" + (int)(Math.random()*7);
        switch (_nota) {
            case "0":
                _nota = "do"; 
                break;
            case "1":
                _nota = "re"; 
                break;
            case "2":
                _nota = "mi"; 
                break;
            case "3":
                _nota = "fa"; 
                break;
            case "4":
                _nota = "sol"; 
                break;
            case "5":
                _nota = "la"; 
                break;
            case "6":
                _nota = "si"; 
                break;
        
            default:
                break;
        }
        return _nota;
    }

    public static int compases(){
        int numCompases = (int)(Math.random()*8);
        return numCompases;
    }
    public static void main(String[] args) {
        int numCompases = compases();
        String primeraNota = nota();
        System.out.print(primeraNota + " ");
        System.out.print(nota() + " ");
        System.out.print(nota() + " ");
        System.out.print(nota() + " | ");
        for (int i = 2; i <= numCompases-1; i++){
            System.out.printf("%s %s %s %s | ", nota(), nota(), nota(), nota());
        }
        System.out.print(nota() + " ");
        System.out.print(nota() + " ");
        System.out.print(nota() + " ");
        System.out.print(primeraNota);
    }
}
