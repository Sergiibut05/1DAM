package ejerciciocompleto;

import java.util.List;

public interface MatriculaCRUD extends CRUD<Matricula>{
    public List<Materia> requestByMateria(Integer id);
    public List<Alumno> requestByAlumno(Integer id);
}
