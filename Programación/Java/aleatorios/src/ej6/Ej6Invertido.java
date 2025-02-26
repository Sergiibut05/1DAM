package ej6;

    //Función para generar los números aleatorios que el robot nos dará para intentar acertar
public class Ej6Invertido {
    public static short getRandom(short max, short min){
        short range = (short)((max - min) + 1);
     		short random = (short) ((range * Math.random()) + min);
		return random;
    }

    //Función para generar las frases que el robot va a decir por cada respuesta
    public static String obtenerRespuesta(int frase) {
        switch (frase) {
            case 1:
                return "¡Vaya, no lo he adivinado esta vez!";
            case 2:
                return "Maldita sea... ¿Cómo puedo fallar?";
            case 3:
                return "Hmm, esto es más complicado de lo que pensé.";
            case 4:
                return "¡Oh no! Volveré a intentarlo.";
            default:
                return "Error: Opción desconocida.";
        }
    }
    public static void main(String[] args) {
        String cursiva = "\033[3m";
        String verde = "\033[32m";
        String reset = "\033[0m";
        String negrita = "\033[1m";
        short numMin = 0;
        short numMax = 100;
        //Pondremos el número en primer lugar para compararlo con el que nos diga el robot más adelante
        System.out.print("Indica un número del 0 al 100 a continuación antes de que se enteré el robot: " + verde);
        final short NUM_ORIGINAL = Short.parseShort(System.console().readLine());
        System.out.println();
        System.out.println(reset + "Hola robot, vamos a jugar a un juego. Voy a pensar en un número del 0 al 100 y tienes que tratar de adivinarlo en el menor número de intentos posibles. Yo solo te diré menor o mayor, vamos a ello.");
        System.out.println();
        //El robot genera el primer número aleatorio y nos lo dice
        short numRobot = getRandom(numMax, numMin);
        System.out.println(cursiva + "- De acuerdo, el primer número que diré es el " + negrita + numRobot + reset);
        //Pongo un contador para contar los intentos que le lleva al robot acertar el número
        short contador = 1;
        //El programa se repetirá siempre que el robot no acierte el número
        while (NUM_ORIGINAL != numRobot) {
            short frase = getRandom((short)4, (short)1);
            System.out.println("1. El número que yo estoy pensando es menor.");
            System.out.println("2. El número que yo estoy pensando es mayor" + verde);
            short opcion = Short.parseShort(System.console().readLine());
            System.out.println();
            switch (opcion) {
                case 1:
                    numMax = (short)(numRobot-1);
                    break;
                case 2:
                    numMin = (short)(numRobot+1);
                default:
                    break;
            }
            numRobot = (short)(getRandom(numMax, numMin));
            System.out.printf("%s %s - %s Ahora diré el%s %d %s %n", reset, cursiva, obtenerRespuesta(frase), negrita, numRobot, reset);
            contador++;
        }
        System.out.println();
        System.out.println("WOW, enhorabuena, lo has adivinado en " + contador + " pasos");
        System.out.println();
        System.out.println(cursiva + "- !Que divertido¡ Juguemos de nuevo " + reset);
    }
}