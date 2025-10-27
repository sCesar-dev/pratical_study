package Cmetodos.test;

import Cmetodos.domain.Pessoa;

public class TestPessoa01 {
    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        Pessoa pessoa3 = new Pessoa();

        pessoa1.setNome("Fulano");
        pessoa1.setIdade(18);
        pessoa1.setSexo('M');
        pessoa1.imprimirDados();

        pessoa1.setNome("Sicrano");
        pessoa1.setIdade(43);
        pessoa1.setSexo('N');
        pessoa1.imprimirDados();

        pessoa1.setNome("Beltrano");
        pessoa1.setIdade(42);
        pessoa1.setSexo('M');
        pessoa1.imprimirDados();


    }
}
