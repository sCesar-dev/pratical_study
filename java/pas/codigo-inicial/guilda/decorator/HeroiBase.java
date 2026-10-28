package guilda.decorator;

/** Componente concreto: o herói "pelado", sem nenhum equipamento. (PRONTO) */
public class HeroiBase implements Heroi {
    private final String nome;
    private final String classe;
    private final int nivel;
    private final int ataque;
    private final int defesa;

    public HeroiBase(String nome, String classe, int nivel, int ataque, int defesa) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    @Override public String getNome()   { return nome; }
    @Override public String getClasse() { return classe; }
    @Override public int getNivel()     { return nivel; }
    @Override public int getAtaque()    { return ataque; }
    @Override public int getDefesa()    { return defesa; }
}
