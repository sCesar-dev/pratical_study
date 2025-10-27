package Dpoo.association.test;

import Dpoo.association.domain.Escola;
import Dpoo.association.domain.Professor;

public class EscolaTest02 {

    public static void main(String[] args) {
        Escola escola1 = new Escola("Educandário");
        Professor professor1 = new Professor("Anderson");
        Professor professor2 = new Professor("Vitor");

        Escola[] escolas = {escola1};

        professor1.setEscolas(escolas);
        professor2.setEscolas(escolas);

        escola1.setNome("Anderson");

        System.out.println("--- Professor ---");
        professor1.imprime();
        professor2.imprime();

        System.out.println("--- Escola ---");
        escola1.imprime();

    }
}
