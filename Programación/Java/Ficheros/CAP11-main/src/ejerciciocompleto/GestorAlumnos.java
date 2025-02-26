package ejerciciocompleto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorAlumnos implements CRUD<Alumno>{
    private HashMap<Integer, Alumno> alumnos;
    private FileManager fileManager;
    static Integer idSig = 1;


    public GestorAlumnos(String fileName) throws IOException{
        fileManager = new FileManager(fileName);
        alumnos = new HashMap<>();
        Integer idMax = Integer.MIN_VALUE;
        for(String linea : fileManager.leer()){
            Alumno alumno = AlumnoSerializable.deserialize(linea);
            alumnos.put(alumno.getId(), alumno);
            if(alumno.getId() > idMax)
                idMax = alumno.getId();
        }
        idSig = idMax+1;
    }

    private boolean save(){
        ArrayList<String> volcar = new ArrayList<>();
        for(Alumno al: alumnos.values()){
            volcar.add(al.serialize());
        }
        try {
            fileManager.escribir(volcar);    
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer create(Alumno elem) throws ElementExists, IOException{
        if(alumnos.containsKey(elem.getId()))
            throw new ElementExists();
        Alumno alumno = new Alumno(idSig, elem.getApellidos(), elem.getNombre(), elem.getNie(), elem.getGrupoId());
        alumnos.put(idSig, alumno);
        if(!save()){
            alumnos.remove(alumno.getId());
            throw new IOException();
        }
        return idSig++;
    }

    @Override
    public List<Alumno> requestAll() {
        ArrayList<Alumno> copia = new ArrayList<>();
        for(Alumno alumno: alumnos.values()){
            copia.add(alumno);
        }
        return copia;
    }

    @Override
    public Alumno update(Integer id, Alumno elem) throws ElementDoesNotExist, IOException{
        if(!alumnos.containsKey(id))
            throw new ElementDoesNotExist();
        Alumno alumno = alumnos.get(id);
        alumnos.put(id, elem);
        if(!save()){
            alumnos.put(id, alumno);
            throw new IOException();
        }
        return alumno;
    }

    @Override
    public Alumno delete(Integer id) throws ElementDoesNotExist, IOException{
        if(!alumnos.containsKey(id))
            throw new ElementDoesNotExist();
        Alumno alumno = alumnos.remove(id);
        if(!save()){
            alumnos.put(alumno.getId(), alumno);
            throw new IOException();
        }
        return alumno;
    }
    
}
