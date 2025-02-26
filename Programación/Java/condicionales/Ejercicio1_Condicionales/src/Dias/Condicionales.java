package Dias;

public class Condicionales {
    private String dia;
    public Condicionales(String dia){
        this.dia=dia;
    }
    public String getdia(){
        return dia;
    }
    public void setdia(String dia){
        this.dia = dia;
    }
    public static String condicion(Condicionales Condicionales){
        String resultado;
        switch(Condicionales.dia){
            case "lunes":
            resultado =("El lunes a primera toca Programación");
            break;
            case "martes":
            resultado =("El martes a primera toca IPE 1");
            break;
            case "miercoles":
            resultado =("El miercoles a primera toca Programación");
            break;
            case "jueves":
            resultado =("El jueves a primera toca Lenguaje de Marcas");
            break;
            case "viernes":
            resultado =("El viernes a primera toca Lenguaje de Marcas");
            break;
            default:
            resultado = "Dia no valido";
            break;
        }
        return resultado;
    }
    
}
