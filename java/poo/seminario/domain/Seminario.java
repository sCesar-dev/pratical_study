package Dpoo.seminario.domain;

public class Seminario {
    private String titulo;

    private Estudante[] estudantes;

    private Local local;

    public void imprime(){
        System.out.println("\n--- Seminário ---");
        System.out.println("\nNome do seminário: " + this.titulo);
        System.out.println("\nLocal: " + this.local);
        if (estudantes == null) return;
        for (Estudante estudante: estudantes){
            System.out.println("Nome: " + estudante.getNome() +
                    "\nIdade: " + estudante.getIdade());
        }
    }

    public Seminario(String titulo, Estudante[] estudantes, Local local) {
        this.titulo = titulo;
        this.estudantes = estudantes;
        this.local = local;
    }

    public Seminario(String titulo, Estudante[] estudantes) {
        this.titulo = titulo;
        this.estudantes = estudantes;
    }

    public void setEstudantes(Estudante[] estudantes) {
        this.estudantes = estudantes;
    }

    public Seminario(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
