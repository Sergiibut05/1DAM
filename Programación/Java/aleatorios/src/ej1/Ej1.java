package ej1;

public class Ej1 {
    public static int getRandom(int min, int max) {
		int range = (max - min) + 1;
     		int random = (int) ((range * Math.random()) + min);
		return random;
	}
    public static void main(String[] args) {
        System.out.print("Tirada de tres dados: ");
        int tirada1 = getRandom(1,6);
        int tirada2 = getRandom(1,6);
        int tirada3 = getRandom(1,6);
        System.out.printf("%d %d %d %n", tirada1, tirada2, tirada3);
        System.out.printf("Suma: %d", tirada1+tirada2+tirada3);
    }
}
