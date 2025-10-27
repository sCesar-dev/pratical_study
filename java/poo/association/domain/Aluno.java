package Dpoo.association.domain;

public class Aluno {
    private final String nome;

    private Faculdade faculdade;

    public Aluno(String nome){
        this.nome = nome;
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public void imprimir(){
        System.out.println("\nNome do aluno: " + this.nome);
        if (faculdade != null){
            System.out.println("Nome da faculdade: " + faculdade.getNome());
        }else {
            System.out.println("Sem faculdade");
        }
    }



}
