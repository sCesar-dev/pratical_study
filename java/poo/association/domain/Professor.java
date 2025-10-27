package Dpoo.association.domain;

public class Professor {
    private String nome;

    private Escola[] escolas;

    public Professor(String nome) {
        this.nome = nome;
    }

    public Professor(String nome, Escola[] escolas) {
        this.nome = nome;
        this.escolas = escolas;
    }

    public void imprime(){
        System.out.println(this.nome);
        if (escolas == null) return;
        for (Escola escola: escolas){
            System.out.println(escola.getNome());
        }
    }

    public Escola[] getEscolas() {
        return escolas;
    }

    public void setEscolas(Escola[] escolas) {
        this.escolas = escolas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
