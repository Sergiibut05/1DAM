package ejercicio3_2bloque;

public class ColeccionDiscos implements DiscosCrud{

    protected Disco[] discos;
    public ColeccionDiscos(int tamanio){
        discos = new Disco[tamanio];
    }

    private static int encontrarHueco(Disco[] discos){
        int hueco = -1;
        int posicion = 0;
        while(posicion < discos.length && 
              discos[posicion]!=null)
            posicion++;
        if(posicion!=discos.length)
            hueco = posicion;
        return hueco;
    }

    public static int encontrarDisco(Disco[] discos, String id){
        int posicion = 0;
        boolean encontrado = false;
        while(!encontrado && posicion < discos.length){
            if(discos[posicion].getCod().equals(id))
                encontrado = true;
            else
                posicion++;
        }
        return encontrado?posicion:-1;
    }

    @Override
    public Disco crear(Disco d) {
        int posicion = encontrarHueco(discos);
        if(posicion != -1){
            discos[posicion]=d;
            return d;
        }
        return null;
    }

    @Override
    public Disco[] consultar() {
        return discos;
    }

    @Override
    public Disco consultarPorID(String id) {
        int posicion = encontrarDisco(discos, id);
        if(posicion != -1)
            return discos[posicion];
        return null;
    }

    @Override
    public Disco actualizar(String id, Disco d) {
        int posicion = encontrarDisco(discos, id);
        if(posicion!=-1){
            discos[posicion]=d;
            return d;
        }
        return null;
    }

    @Override
    public Disco borrar(String id) {
        int posicion = encontrarDisco(discos, id);
        if(posicion != -1){
            Disco d = discos[posicion];
            discos[posicion] = null;
            return d;
        }
        return null;
    }

    
    
}
