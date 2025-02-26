package ejerciciosalumnos.Ejercicio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileOperations {
    /**
     * Lee el contenido de un archivo y lo vuelca en un Array
     * @return Un array conteniendo cada una de las líneas del archivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String> leer() throws FileNotFoundException, IOException;

    /*
     * Escribe el contenido de una array a un archivo.
     * Si no existe el archivo lo crea
     * Si existe el archivo sobreescribe su contenido.
     */
    public void escribir(List<String> lineas) throws FileNotFoundException, IOException;

    /**
     * Borra el archivo
     * @throws SecurityException
     */
    public void borrarArchivo() throws SecurityException;
}
