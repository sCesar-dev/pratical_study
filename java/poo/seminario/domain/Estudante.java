package Dpoo.seminario.domain;

public class Estudante {
    private String nome;
    private int idade;

    private Seminario seminario;

    public void imprime(){
        System.out.println("--- Estudante ---");
        System.out.println("Nome do estudante: " + this.nome +
                "\nIdade do aluno: " + this.idade);
        if (seminario != null){
            System.out.println(seminario.getTitulo());
        }else {
            System.out.println("Não participará");
        }
    }

    public Estudante(String nome, int idade, Seminario seminario) {
        this.nome = nome;
        this.idade = idade;
        this.seminario = seminario;
    }

    public Estudante(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
