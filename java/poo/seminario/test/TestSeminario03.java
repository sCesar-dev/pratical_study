package Dpoo.seminario.test;

import Dpoo.seminario.domain.Estudante;
import Dpoo.seminario.domain.Seminario;

public class TestSeminario03 {
    public static void main(String[] args) {
        /*
        Um professor para vários seminários
        One teacher to many seminars
         */

        Estudante estudante1 = new Estudante("Fulano", 15);
        Estudante estudante2 = new Estudante("Sicrano", 16);
        Estudante estudante3 = new Estudante("Beltrano", 17);
        Estudante[] estudantes = {estudante1, estudante2, estudante3};
        Seminario seminario1 = new Seminario("Seminário de Java", estudantes);
        seminario1.imprime();



    }
}
