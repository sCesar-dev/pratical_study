package pas;

public class Estoque extends AbstractSubject {
    private int total;

    public Estoque(int total) {
        super();
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void incrementaEstoque(int quantidade) {
        this.total += quantidade;
        this.notifyAll_();
    }
}
