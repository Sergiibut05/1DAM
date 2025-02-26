package ejercicio5;

import java.util.ArrayList;

public class ColeccionDiscos implements DiscosCrud{

    protected ArrayList<Disco> discos;
    public ColeccionDiscos(int tamanio){
        discos = new ArrayList<Disco>();
    }

    @Override
    public Disco crear(Disco d) {
        discos.add(d);
        return d;
    }

    @Override
    public Disco[] consultar() {
        Disco[] ret = new Disco[discos.size()];
        discos.toArray(ret);
        return ret;
    }

    @Override
    public Disco consultarPorID(String id) {
        int index = discos.indexOf(new Disco(id));
        return (index==-1)?null:discos.get(index);
    }

    @Override
    public Disco actualizar(String id, Disco d) {
        int index = discos.indexOf(new Disco(id));
        if(index!=-1){
            Disco anterior = discos.get(index);
            discos.set(index, d);
            return anterior;
        }
        return null;
    }

    @Override
    public Disco borrar(String id) {
        int index = discos.indexOf(new Disco(id));
        if(index!=-1){
            Disco anterior = discos.get(index);
            discos.remove(index);
            return anterior;
        }
        return null;
    }

    
    
}
