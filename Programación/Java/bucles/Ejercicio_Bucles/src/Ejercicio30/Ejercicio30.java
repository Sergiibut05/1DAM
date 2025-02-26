package Ejercicio30;

public class Ejercicio30 {
    public static void ejercicio30(){
        System.out.println("Por favor, introduzca la primera hora.");
        int diainicial = pedirdia();
        int horainicial =pedirhora();
        System.out.println("Por favor, introduzca la segunda hora.");
        int diafinal = pedirdia();
        int horafinal = pedirhora();
        String dia1=calculardia(diainicial);
        String dia2=calculardia(diafinal);
        int resultado=calculohora(diainicial, diafinal, horainicial, horafinal);
        System.out.printf("Entre las %dh del %s y las %dh del %s hay %d hora/s.",horainicial,dia1,horafinal,dia2,resultado);
    }
    public static int pedirdia(){
        int dia=0;
        System.out.printf("Dia: ");
        boolean diacorrecto=false;
        while (!diacorrecto){
            String diaSemana=System.console().readLine();
            dia = switch(diaSemana.toLowerCase()){
                case"lunes"->1;
                case"martes"->2;
                case"miercoles"->3;
                case"jueves"->4;
                case"viernes"->5;
                case"sabado"->6;
                case"domingo"->7;
                default->0;
            };
            if (dia==0){
                System.out.println("No se ha introducido correctamente el día de la semana.%nLos días válidos son: lunes, martes, miércoles, jueves, viernes, sábado y domingo.");
            }
            if(dia!=0){
                diacorrecto=true;
            }
        }
        System.out.println();
        return dia;
        
    }
    public static int pedirhora(){
        int hora=-1;
        System.out.printf("Hora: ");
        
        while (hora<1 || hora>24){
            hora= Integer.parseInt(System.console().readLine());
        }
        System.out.println();
        return hora;
    }
    public static int calculohora(int diainicial, int diafinal, int horaincial, int horafinal){
        int resultado=0;
        if(diafinal>diainicial){
            resultado=((diafinal-diainicial-1)*24)+(24-horaincial)+(horafinal);
        }else{
            resultado=((7-diainicial)*24)+(24-horaincial)+((diafinal-1)*24)+(horafinal);
        }
        return resultado;
    }
    public static String calculardia(int dia){
        
        String diasemana = switch(dia){
            case 1->"lunes";
            case 2->"martes";
            case 3 ->"miercoles";
            case 4->"jueves";
            case 5->"viernes";
            case 6->"sabado";
            case 7->"domingo";
            default->"Ninguno";
        };
        return diasemana;
    }
}
