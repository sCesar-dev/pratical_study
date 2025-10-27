package Dpoo.association.test;

import Dpoo.association.domain.Escola;
import Dpoo.association.domain.Professor;

public class EscolaTest01 {
    public static void main(String[] args) {
        Professor professor1 = new Professor("Charles");
        Professor professor2 = new Professor("Eduard");
        Professor professor3 = new Professor("Andrew");
        Professor professor4 = new Professor("John");
        Professor[] professores = {professor1, professor2, professor3, professor4};
        Escola escola = new Escola("Dom Bosco", professores);
        escola.imprime();


    }
}
