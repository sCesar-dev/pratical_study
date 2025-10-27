package Dpoo.seminario.test;

import Dpoo.seminario.domain.Estudante;
import Dpoo.seminario.domain.Seminario;

public class TestSeminario01 {
    public static void main(String[] args) {
        /*
        Um aluno para um seminário
        One student to one seminary
         */
        Seminario seminario = new Seminario("Cibersecurity seminary");
        Estudante estudante1 = new Estudante("João", 15);
        Estudante estudante2 = new Estudante("Ana", 13, seminario);
        Estudante estudante3 = new Estudante("Samuel", 20, seminario);
        estudante1.imprime();
        estudante2.imprime();
        estudante3.imprime();

    }
}
