package ejerciciocompleto;

import java.io.IOException;
import java.util.List;

public class GetorMatriculas implements MatriculaCRUD{

    @Override
    public Integer create(Matricula elem) throws ElementExists, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<Matricula> requestAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestAll'");
    }

    @Override
    public Matricula update(Integer id, Matricula elem) throws ElementDoesNotExist, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Matricula delete(Integer id) throws ElementDoesNotExist, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Materia> requestByMateria(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestByMateria'");
    }

    @Override
    public List<Alumno> requestByAlumno(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestByAlumno'");
    }
    
}
