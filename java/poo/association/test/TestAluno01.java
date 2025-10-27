package Dpoo.association.test;

import Dpoo.association.domain.Aluno;
import Dpoo.association.domain.Faculdade;

public class TestAluno01 {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("Eduardo");
        Aluno aluno2 = new Aluno("Ana");
        Aluno aluno3 = new Aluno("Carla");
        Aluno aluno4 = new Aluno("Mara");
        Aluno aluno5 = new Aluno("Rafael");
        Aluno aluno6 = new Aluno("João");
        
        Faculdade faculdade = new Faculdade("Unifacs");

        aluno1.setFaculdade(faculdade);
        aluno6.setFaculdade(faculdade);
        aluno4.setFaculdade(faculdade);


        Aluno[] alunos= {aluno1, aluno2, aluno3, aluno4, aluno5, aluno6};

        for (Aluno aluno: alunos) {
            aluno.imprimir();
        }
    }
}
