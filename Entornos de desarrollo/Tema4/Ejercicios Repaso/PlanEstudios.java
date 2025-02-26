import java.util.ArrayList;
import java.util.List;

public class PlanEstudios {

    // atributos irían aquí


    // esta es la relación de composición
    private Asignatura[] asignaturasArray;

    public PlanEstudios() {
        // esta es la relación de composición
        // hay que crear los componentes en el constructor para que
        // tengan el mismo tiempo de vida que el compuesto
        asignaturasArray =
                new Asignatura[]{new Asignatura(), new Asignatura()};
    }
}
