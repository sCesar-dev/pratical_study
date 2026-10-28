package guilda;

import guilda.decorator.*;
import guilda.filtro.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Heroi> herois = new ArrayList<>();
        herois.add(new EscudoDeCarvalho(new EspadaFlamejante(
                new HeroiBase("Thorin", "Guerreiro", 5, 10, 6))));
        herois.add(new EspadaFlamejante(
                new HeroiBase("Merlin", "Mago", 6, 14, 3)));
        herois.add(new ArmaduraPesada(new EspadaFlamejante(
                new HeroiBase("Brunhilda", "Guerreiro", 7, 14, 8))));
        herois.add(new EspadaFlamejante(
                new HeroiBase("Lyra", "Arqueiro", 4, 13, 4)));
        herois.add(new HeroiBase("Gorak", "Guerreiro", 3, 9, 7));
        herois.add(new EscudoDeCarvalho(new EspadaFlamejante(
                new HeroiBase("Elara", "Mago", 5, 9, 5))));

        // ---------- DECORATOR (já pronto): os atributos já vêm somados dos equipamentos ----------
        System.out.println("=== Heróis equipados (Decorator pronto) ===");
        herois.forEach(h -> System.out.println(descrever(h)));
        // Saída:
        // Thorin (Guerreiro) ATK 18 | DEF 11
        // Merlin (Mago) ATK 22 | DEF 3
        // Brunhilda (Guerreiro) ATK 20 | DEF 15
        // Lyra (Arqueiro) ATK 21 | DEF 4
        // Gorak (Guerreiro) ATK 9 | DEF 7
        // Elara (Mago) ATK 17 | DEF 10

        // ---------- PARTE 1: Filter ----------
        System.out.println("\n=== PARTE 1: critérios simples ===");
        mostrar("Guerreiros", new CriterioClasse("Guerreiro").filtrar(herois));
        // Esperado: Thorin, Brunhilda, Gorak
        mostrar("Ataque >= 15", new CriterioAtaqueMinimo(15).filtrar(herois));
        // Esperado: Thorin, Merlin, Brunhilda, Lyra, Elara

        // ---------- PARTE 2: Composição ----------
        System.out.println("\n=== PARTE 2: missões ===");

        // Missão 1 - Vanguarda: ataque total >= 15 E defesa total >= 10
        Criterio vanguarda = new CriterioE(new CriterioAtaqueMinimo(15), new CriterioDefesaMinimo(10));
        mostrar("Missão 1 - Vanguarda", vanguarda.filtrar(herois));
        // Esperado: Thorin, Brunhilda, Elara

        // Missão 2 - Sem conjuradores: vanguarda E NÃO classe "Mago"
        Criterio semConjuradores = new CriterioE(vanguarda, new CriterioNao(new CriterioClasse("Mago")));
        mostrar("Missão 2 - Sem conjuradores", semConjuradores.filtrar(herois));
        // Esperado: Thorin, Brunhilda

        // Exemplo de composição: heróis que são Guerreiros OU têm defesa total >= 12.
        Criterio missao3 = new CriterioOu(new CriterioClasse("Guerreiro"), new CriterioDefesaMinimo(12));
        mostrar("Missão 3 - Bônus", missao3.filtrar(herois));
        // Esperado: Thorin, Brunhilda, Gorak

    }

    private static String descrever(Heroi h) {
        return h.getNome() + " (" + h.getClasse() + ") ATK " + h.getAtaque()
                + " | DEF " + h.getDefesa();
    }

    private static void mostrar(String titulo, List<Heroi> lista) {
        StringBuilder sb = new StringBuilder(titulo + ": ");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(i > 0 ? ", " : "").append(lista.get(i).getNome());
        }
        System.out.println(lista.isEmpty() ? titulo + ": (vazio)" : sb);
    }
}
