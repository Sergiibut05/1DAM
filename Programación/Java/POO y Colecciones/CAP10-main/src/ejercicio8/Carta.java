package ejercicio8;

public class Carta implements Comparable<Carta>{
    protected int codigo;

    public Carta(int i){
        this.codigo = i;
    }

    public Carta(String numero, String palo) throws Exception{
        int valorNumero = switch(numero.toLowerCase()){
            case "as"->0;
            case "dos"->1;
            case "tres"->2;
            case "cuatro"->3;
            case "cinco"->4;
            case "seis"->5;
            case "siete"->6;
            case "sota"->7;
            case "caballo"->8;
            case "rey"->9;
            default->throw new Exception("Carta inválida");
        };
        int valorPalo = switch(palo.toLowerCase()){
            case "oros"->0;
            case "copas"->1;
            case "espadas"->2;
            case "bastos"->3;
            default->throw new Exception("Carta inválida");
        };
        this.codigo = valorPalo*10+valorNumero;
    }

    public int getCodigo() {
        return codigo;
    }

    /**
     * Devuelve a partir de un núero del 1 al 40 el palo correspondiente
     * 1-10 oros
     * 11-20 copas
     * 21-30 espadas
     * 31-40 bastos
     * @param carta
     * @return
     */
    public static String getPalo(int carta){
        return switch((int)((carta-1)/10)){
            case 0->"oros";
            case 1->"copas";
            case 2->"espadas";
            case 3->"bastos";
            default->"";
        };
    }

    /**
     * Devuelve el número de una carta del 1 al 40
     * uno, dos, tres, ..., siete, sota, caballo, rey
     * @param carta
     * @return
     */
    public static String getNumero(int carta){
        return switch((int)((carta-1)%10)){
            case 0->"as";
            case 1->"dos";
            case 2->"tres";
            case 3->"cuatro";
            case 4->"cinco";
            case 5->"seis";
            case 6->"siete";
            case 7->"sota";
            case 8->"caballo";
            case 9->"rey";
            default->"";
        };
    }

    @Override
    public String toString() {
        return String.format("%s de %s", getNumero(codigo), getPalo(codigo));
    }

    
    @Override
    public int compareTo(Carta o) {
        if(this.codigo == o.codigo)
            return 0;
        else{
            if(getPalo(this.codigo).equals(getPalo(o.codigo))){
                return getNumero(codigo).compareTo(getNumero(o.codigo));
            }
            return getPalo(this.codigo).compareTo(getPalo(o.codigo));
        }
    }

    

    
}
