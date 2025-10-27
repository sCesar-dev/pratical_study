package basicSintax.tests;

import basicSintax.domain.Automovel;

public class TestAutomovel01 {
    public static void main(String[] args) {
        Automovel automovel01 = new Automovel("Corsa", "Azul", 10, 100);
        automovel01.calcEconomy();
    }
}
