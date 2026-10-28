package guilda.filtro;

import guilda.decorator.Heroi;
import java.util.ArrayList;
import java.util.List;

/**
 * PARTE 2 - TODO: inverte um critério (fica quem NÃO atende).
 * Atenção: o resultado precisa partir da lista ORIGINAL recebida.
 */
public class CriterioNao implements Criterio {
    private final Criterio criterio;

    public CriterioNao(Criterio criterio) {
        this.criterio = criterio;
    }

    @Override
    public List<Heroi> filtrar(List<Heroi> herois) {
        // TODO
        List<Heroi> atendeCriterio = criterio.filtrar(herois);
        List<Heroi> resultado = new ArrayList<>();
        for(Heroi h: herois){
            if(!atendeCriterio.contains(h)) {
                resultado.add(h);
            }
        }
        return resultado;
    }
}
