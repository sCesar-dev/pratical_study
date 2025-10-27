package Dpoo.seminario.domain;

public class Professor {
    private String nome, especialidade;

    private Seminario[] seminarios;

    public void imprime(){
        System.out.println("--- Professor ---");
        if (seminarios == null) return;
        for (Seminario seminario: seminarios){
            System.out.println("Nome do Professor: " + this.nome +
                    "Especialidade do professor: " + this.especialidade +
                    "Título do seminário: " + seminario.getTitulo());
        }
    }

    public Professor(String nome, String especialidade, Seminario[] seminarios) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.seminarios = seminarios;
    }

    public Professor(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Seminario[] getSeminarios() {
        return seminarios;
    }

    public void setSeminarios(Seminario[] seminarios) {
        this.seminarios = seminarios;
    }
}
