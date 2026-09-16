package simuladoProva;

public class Pesquisador {
    private String matricula;
    private String name;

    public String getMatricula() {
        return matricula;
    }

    public String getName() {
        return name;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Matricula: [" + this.matricula + "] - Nome: [" + this.name + "]";
    }
}
