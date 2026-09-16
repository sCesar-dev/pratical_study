package simuladoProva;

/*1. Classe Equipamento

Representa as peças disponíveis no laboratório.

    Atributos (privados): String codigo, String nome, double valor.

    Métodos:

        Getters e Setters para todos os atributos.

        Sobrescrever o método toString() para retornar: "Código: [codigo] - Equipamento: [nome]".
*/
public class Equipamento {
    private String codigo;
    private String nome;
    private double valor;
    
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override 
    public String toString() {
        return "Codigo: [" + this.codigo + "] - Equipamento: [" + this.nome + "]";
    }
}
