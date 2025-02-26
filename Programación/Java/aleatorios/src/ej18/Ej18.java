package ej18;

public class Ej18 {
    public static String color(){
        String _color = "";
        _color += (int)(Math.random()*6);
        switch (_color) {
            case "0":
                _color = "rojo";
                break;
            case "1":
                _color = "azul";
                break;
            case "2":
                _color = "verde";
                break;
            case "3":
                _color = "amarillo";
                break;
            case "4":
                _color = "violeta";
                break;
            case "5":
                _color = "naranja";
                break;
        
            default:
                break;
        }
        return _color;
    }
    public static void main(String[] args) {
        String color1 = color();
        String color2 = color();
        String color3 = color();
        while (color2 == color1) {
            color2 = color();
        }
        while (color3 == color1 || color3 == color2) {
            color3 = color();
        }
        System.out.printf("%s, %s, %s", color1, color2, color3);
    }   
}
