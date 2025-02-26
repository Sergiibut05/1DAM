package ej8;

public class Ej8 {
    public static String pintarTemperatura(int temperatura){
        String raya = "";
        for(int i = 0; i < temperatura; i++){
            raya += "\u25A0";
        }
        return raya;
    }


    public static int numAleatorio(){
        return (int)(Math.random()*41);
    }

    public static void main(String[] args) {
        int[] temperatura = new int[12];
        for (int i = 0; i <= 11; i++){
            temperatura[i] = numAleatorio();
        }
        String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        for(int i = 0; i <= 11; i++){
            System.out.printf("Introduzca la temperatura media de %s: %d%n", meses[i], temperatura[i]);
        }

        for (int i = 0; i <= 11; i++){
            String temperaturaPintar = pintarTemperatura(temperatura[i]);
            System.out.printf("%12s | %s%n", meses[i], temperaturaPintar);
        }
        ///u25A0
    }
}
