package Dpoo.seminario.test;

import Dpoo.seminario.domain.Estudante;
import Dpoo.seminario.domain.Seminario;

public class TestSeminario02 {
    public static void main(String[] args) {
        /*
        Um seminário para nenhum ou vários alunos
        One seminary to somobody or many students
         */
        Estudante estudante1 = new Estudante("Anderson", 15);
        Estudante estudante2 = new Estudante("Jonas", 12);
        Estudante estudante3 = new Estudante("Joana", 14);
        Estudante estudante4 = new Estudante("Júlia", 19);
        Estudante[] estudantes = {estudante1, estudante2, estudante3, estudante4};
        Seminario seminario = new Seminario("Seminário de Java", estudantes);
        seminario.imprime();
    }
}
