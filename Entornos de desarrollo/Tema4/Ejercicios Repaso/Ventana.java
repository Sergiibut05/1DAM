import java.util.ArrayList;
import java.util.List;

public class Ventana {

    private Cabecera cabecera;
    private BarraDesplazamiento[] barrasDesplazamiento;
    private Panel panel;

    public Ventana() {
        cabecera = new Cabecera();
        barrasDesplazamiento =
                new BarraDesplazamiento[] {new BarraDesplazamiento(), new BarraDesplazamiento()};
        panel = new Panel();
    }

}
