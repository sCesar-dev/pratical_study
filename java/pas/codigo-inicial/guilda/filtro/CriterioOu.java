package guilda.filtro;

import guilda.decorator.Heroi;
import java.util.ArrayList;
import java.util.List;

/**
 * EXEMPLO (PRONTO): composição de critérios.
 * Um herói entra quando atende ao critério A OU ao critério B.
 * A implementação mantém a ordem original da lista e evita duplicatas,
 * servindo como referência para outros critérios compostos.
 */
public class CriterioOu implements Criterio {
    private final Criterio a;
    private final Criterio b;

    public CriterioOu(Criterio a, Criterio b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public List<Heroi> filtrar(List<Heroi> herois) {
        List<Heroi> atendeA = a.filtrar(herois);
        List<Heroi> atendeB = b.filtrar(herois);

        List<Heroi> resultado = new ArrayList<>();
        for (Heroi h : herois) {
            if (atendeA.contains(h) || atendeB.contains(h)) {
                resultado.add(h);
            }
        }
        return resultado;
    }
}
