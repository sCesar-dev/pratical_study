package guilda.filtro;

import guilda.decorator.Heroi;
import java.util.ArrayList;
import java.util.List;

/** EXEMPLO (PRONTO): defesa total >= minimo. Use como modelo. */
public class CriterioDefesaMinimo implements Criterio {
    private final int minimo;

    public CriterioDefesaMinimo(int minimo) {
        this.minimo = minimo;
    }

    @Override
    public List<Heroi> filtrar(List<Heroi> herois) {
        List<Heroi> resultado = new ArrayList<>();
        for (Heroi h : herois) {
            if (h.getDefesa() >= minimo) {
                resultado.add(h);
            }
        }
        return resultado;
    }
}
