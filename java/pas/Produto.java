package pas;

public class Produto implements IF_Observer {
    private AbstractSubject servico;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade, AbstractSubject servico) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.servico = servico;
    }

    public double getTotal() {
        return this.preco * this.quantidade;
    }

    public AbstractSubject getServico() {
        return this.servico;
    }

    public void setServico(AbstractSubject servico) {
        this.servico = servico;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public void update() {
        if (this.servico instanceof Estoque) {
            this.quantidade = ((Estoque) this.servico).getTotal();
        }
    }

    @Override
    public String toString() {
        return "Produto [nome=" + this.nome + ", preço=" + this.preco + ", quantidade=" + this.quantidade + "] Total: " + this.getTotal();
    }
}
