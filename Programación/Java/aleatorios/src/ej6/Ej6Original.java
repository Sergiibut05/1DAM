package ej6;

public class Ej6Original {
    public static void main(String[] args) {
        short numAleatorio = (short)(Math.random() * 101);
        System.out.println("Estoy pensando un número del 0 al 100, intenta adivinarlo. Tienes 5 oportunidades.");
        short num = 0;
        short contadorIntentos = 0;
        for (int i = 1; i <= 5; i++){
            System.out.print("Introduce un número: ");
            num = Short.parseShort(System.console().readLine());
            if (num < numAleatorio) {
                System.out.println("El número que estoy pensando es mayor que " + num);
            }else if (num > numAleatorio){
                System.out.println("El número que estoy pensando es menor que " + num);
            }else{
                System.out.println("¡Enhorabuena! ¡Has acertado!");
                contadorIntentos++;
                System.out.println("Lo has conseguido en " + contadorIntentos + " intentos");
            }
            contadorIntentos++;
        }
        if (num!=numAleatorio) {
            System.out.println("Lo siento, no has acertado, el número que estaba pensando era el " + numAleatorio);
        }
    }
}