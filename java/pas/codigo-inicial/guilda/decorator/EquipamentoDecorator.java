package guilda.decorator;

/**
 * Decorator abstrato. (PRONTO)
 * Por padrão, repassa tudo para o herói que ele envolve.
 * Os equipamentos concretos só sobrescrevem o que querem alterar.
 */
public abstract class EquipamentoDecorator implements Heroi {
    protected final Heroi heroi;

    protected EquipamentoDecorator(Heroi heroi) {
        this.heroi = heroi;
    }

    @Override public String getNome()   { return heroi.getNome(); }
    @Override public String getClasse() { return heroi.getClasse(); }
    @Override public int getNivel()     { return heroi.getNivel(); }
    @Override public int getAtaque()    { return heroi.getAtaque(); }
    @Override public int getDefesa()    { return heroi.getDefesa(); }
}
