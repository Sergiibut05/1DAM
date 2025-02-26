package ejerciciosalumnos.Ejercicio1;

import java.util.List;

public interface GFOperations {
    public FraseFlamenca anadeFrase(String frase);

    public boolean eliminaFrase(Integer id);

    public FraseFlamenca obtenerPorID(Integer id);

    public List<FraseFlamenca> listar();

    public boolean guardar();

    public boolean borrarFichero();
}
