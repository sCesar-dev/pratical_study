package basicSintax.tests;

import basicSintax.domain.Prestacao;

public class TestPrestacao01 {
    public static void main(String[] args) {
        Prestacao prestacao = new Prestacao(1499.90, 2, 12);
        prestacao.calcValue();
    }
}
