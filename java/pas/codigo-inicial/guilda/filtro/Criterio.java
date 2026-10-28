package guilda.filtro;

import guilda.decorator.Heroi;
import java.util.List;

/**
 * EXEMPLO (PRONTO): interface do Filter.
 * Recebe uma lista de heróis e devolve APENAS os que atendem ao critério,
 * sem alterar a lista recebida.
 */
public interface Criterio {
    List<Heroi> filtrar(List<Heroi> herois);
}
