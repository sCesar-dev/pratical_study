package guilda.filtro;

import guilda.decorator.Heroi;
import java.util.ArrayList;
import java.util.List;

/**
 * PARTE 2 - TODO: o herói precisa atender aos DOIS critérios (A e B).
 * Pense: dá para encadear um filtro no resultado do outro?
 */
public class CriterioE implements Criterio {
    private final Criterio a;
    private final Criterio b;

    public CriterioE(Criterio a, Criterio b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public List<Heroi> filtrar(List<Heroi> herois) {
        // TODO
        List<Heroi> atendeA = a.filtrar(herois);
        List<Heroi> atendeB = b.filtrar(herois);

        List<Heroi> resultado = new ArrayList<>();
        for(Heroi h: herois){
            if(atendeA.contains(h) && atendeB.contains(h)) {
                resultado.add(h);
            }
        }
        return resultado;
    }
}
