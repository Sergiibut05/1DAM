package ejercicio6;

public class Tiempo {
    private int segundos;

    private Tiempo(int segundos){
        this.segundos = segundos;
    }
    public Tiempo(int horas, int minutos, int segundos){
        this.segundos = horas*3600+minutos*60+segundos;
    }

    private int getHoras(){
        return segundos/3600;
    }

    private int getMinutos(){
        return (segundos%3600)/60;
    }

    private int getSegundos(){
        return (segundos%3600)%60;
    }

    private Tiempo _suma(int seg){
        Tiempo res = new Tiempo(segundos+seg);
        return new Tiempo(
            res.getHoras(), 
            res.getMinutos(), 
            res.getSegundos());
    }
    public Tiempo suma(Tiempo t){
        return _suma(t.segundos);
    }

    public Tiempo resta(Tiempo t){
        return _suma(-t.segundos);
    }

    @Override
    public String toString(){
        return String.format(
            "%s%dh %dm %ds%s", 
            segundos<0?"-(":"",
            Math.abs(getHoras()), 
            Math.abs(getMinutos()), 
            Math.abs(getSegundos()),
            segundos<0?")":"");
    }
    
}
