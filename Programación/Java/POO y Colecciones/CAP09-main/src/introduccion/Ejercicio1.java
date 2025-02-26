package introduccion;
public class Ejercicio1 extends Object{
    String nombre;
    public Ejercicio1(String nombre){
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return "Hola soy "+nombre;
    }

}