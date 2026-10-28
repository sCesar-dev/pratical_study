package guilda.decorator;

/** Componente do Decorator: tudo que um herói (ou um herói equipado) sabe informar. */
public interface Heroi {
    String getNome();
    String getClasse();
    int getNivel();
    int getAtaque();
    int getDefesa();
}
