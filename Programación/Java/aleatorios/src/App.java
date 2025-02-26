public class App {
    public static int num(){
        return (int)((Math.random()*301)-100);
    }
    public static void main(String[] args) throws Exception {
        int numero = num();
        int total = 0;
        int numMax = 0;
        int numMin = 0;
        for (int i = 1; i<=50; i++){
            numero = num();
            System.out.print(numero + " ");
            total += numero;
            if (numero % 2 == 0) {
                numMax = Math.max(numMax, numero);
            }else{
                numMin = Math.min(numMin, numero);
            }
        }
        System.out.println();
        System.out.printf("Máximo de los pares: %d%n", numMax);
        System.out.printf("Mínimo de los impares: %d%n", numMin);
        System.out.printf("Media: %d", total/50);
    }
}
