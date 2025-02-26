package ej31;

public class Ej31 {
    public static int dado1() {
        return (int) ((Math.random() * 6) + 1);
    }

    public static int dado2() {
        return (int) ((Math.random() * 6) + 1);
    }

    public static void main(String[] args) {
        System.out.print("¿Cuánto dinero quiere apostar?: ");
        int apuesta = Integer.parseInt(System.console().readLine());
        
        int dado1 = dado1();
        int dado2 = dado2();
        int sumaDados = dado1 + dado2;

        System.out.printf("Dado 1: %d%n", dado1);
        System.out.printf("Dado 2: %d%n", dado2);
        System.out.println("Suma de los dados: " + sumaDados);

        if (sumaDados == 7 || sumaDados == 11) {
            System.out.printf("¡Enhorabuena! ¡Ha ganado otros %d €!%n", apuesta);
        } else if (sumaDados == 2 || sumaDados == 3 || sumaDados == 12) {
            System.out.println("Lo siento, ha perdido todo su dinero");
        } else {
            System.out.printf("Tiene que seguir tirando, debe conseguir el %d para ganar.%n", sumaDados);
            System.out.println("Si obtiene un 7, perderá la partida.");

            int ganador = sumaDados; // Número a alcanzar para ganar
            do {
                System.out.println("Pulse INTRO para tirar los dados");
                System.console().readLine();

                dado1 = dado1();
                dado2 = dado2();
                sumaDados = dado1 + dado2;

                System.out.printf("Dado 1: %d%n", dado1);
                System.out.printf("Dado 2: %d%n", dado2);
                System.out.println("Suma de los dados: " + sumaDados);

                if (sumaDados == ganador) {
                    System.out.printf("¡Enhorabuena! ¡Ha ganado otros %d €!%n", apuesta);
                    break; // Termina el bucle si gana
                } else if (sumaDados == 7) {
                    System.out.println("Lo siento, ha perdido todo su dinero");
                    break; // Termina el bucle si pierde
                }
            } while (true);
        }
    }
}
