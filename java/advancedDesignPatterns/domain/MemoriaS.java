package advancedDesignPatterns.domain;

public class MemoriaS {
    private static final int BYTE = 1;
    private static final int KB = 1024;
    private static final int MB = KB * 1024;
    private static final int GB = MB * 1024;

    private double total;
    private double utilizadoKB;
    private int unidade;
    
    public MemoriaS(int newTotal, int newUnidade) {
        this.total = newTotal;
        this.unidade = newUnidade;
        this.utilizadoKB = 0;
    }

    public MemoriaS(int newTotal) {
        this.total = newTotal;
    }

    public abstract double getPerda() {
        return this.utilizadoKB / 100;
    }

    public abstract double getEspacoDisponivelRealKB() {
        return this.total - this.utilizadoKB;
    }

    public double getEspacoDisponivelKB() {
        
    }


}
