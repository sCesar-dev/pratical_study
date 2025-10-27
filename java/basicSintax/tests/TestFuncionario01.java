package basicSintax.tests;

import basicSintax.domain.Funcionario;

public class TestFuncionario01 {
    public static void main(String[] args) {
        Funcionario funcionario1 = new Funcionario("Fulano", 18, 1300.85);
        Funcionario funcionario2 = new Funcionario("Sicrano", 27, 1570.54);
        Funcionario funcionario3 = new Funcionario("Beltrano", 35, 3700.85);
        Funcionario funcionarioMedia = new Funcionario("", 0, 0.0);

        funcionario1.imprimirDados();

        funcionario2.imprimirDados();

        funcionario3.imprimirDados();

        funcionarioMedia.mediaSalario(funcionario1.getrents(), funcionario2.getrents(), funcionario3.getrents());

    }
}
