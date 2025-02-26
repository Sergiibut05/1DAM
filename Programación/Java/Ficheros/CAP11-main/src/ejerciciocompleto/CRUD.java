package ejerciciocompleto;
import java.io.IOException;
import java.util.List;

public interface CRUD<T> {
    public Integer create(T elem) throws ElementExists, IOException;
    public List<T> requestAll();
    public T update(Integer id, T elem) throws ElementDoesNotExist, IOException;
    public T delete(Integer id) throws ElementDoesNotExist, IOException;
}
