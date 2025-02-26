package ejercicio10;


public class Ejercicio10 {

    static TranslateService translate = new TranslateService();

    public static void menu(){
        System.out.println("1. Traducir");
        System.out.println("2. Salir");
    }

    public static void translate(){
        System.out.print("Indique la palabra a traducir: ");
        String word = System.console().readLine();
        try {
            String translation = translate.translate(word);
            System.out.printf("%s en inglés es %s%n", word, translation);
        } catch (NotInDictionaryException e) {
            System.out.println("No reconozco esa palabra");
        }
    }

    public static void main(String[] args) {
        
        translate.addTranslation("palabra", "word");
        translate.addTranslation("mes", "month");
        translate.addTranslation("día", "day");
        translate.addTranslation("año", "year");
        translate.addTranslation("correr", "run");
        translate.addTranslation("pelota", "ball");
        translate.addTranslation("oscuro", "dark");
        translate.addTranslation("claro", "light");
        translate.addTranslation("suave", "smooth");
        translate.addTranslation("sol", "sun");
        translate.addTranslation("coche", "car");
        translate.addTranslation("compañía", "company");
        translate.addTranslation("negocios", "business");
        translate.addTranslation("emprendedor", "entrepeneur");
        translate.addTranslation("lluvia", "rain");
        translate.addTranslation("gato", "cat");
        translate.addTranslation("perro", "dog");
        translate.addTranslation("casa", "house");
        translate.addTranslation("presidente", "president");
        translate.addTranslation("ayuntamiento", "town hall");
        translate.addTranslation("motocicleta", "motorcycle");
        translate.addTranslation("programa", "program");
        translate.addTranslation("programador", "developer");
        boolean exit = false;
        do {
            menu();
            int option = Integer.parseInt(System.console().readLine());
            switch (option) {
                case 1:
                    translate();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        } while (!exit);
    }
}
