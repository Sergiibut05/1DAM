package Calculo; 
public class Procesos {
    public static void ordenar(int num1,int num2,int num3){
        int menor, medio, mayor;
        try {
            if (num1 <= num2 && num1 <= num3) {
                menor = num1;
                if (num2 <= num3) {
                    medio = num2;
                    mayor = num3;
                } else {
                    medio = num3;
                    mayor = num2;
                }
            } else if (num2 <= num1 && num2 <= num3) {
                menor = num2;
                if (num1 <= num3) {
                    medio = num1;
                    mayor = num3;
                } else {
                    medio = num3;
                    mayor = num1;
                }
            } else {
                menor = num3;
                if (num1 <= num2) {
                    medio = num1;
                    mayor = num2;
                } else {
                    medio = num2;
                    mayor = num1;
                }
            
        }
        System.out.println("Los números introducidos ordenados de menor a mayor son: " + menor + ", " + medio + " y " + mayor);
        } catch (Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
