package Dpoo.constructors.test;

import Dpoo.constructors.Domain.Anime;

public class TestAnime01 {
    public static void main(String[] args) {
        Anime anime1 = new Anime("Dragon Ball Z", "Goku", 'M');
        anime1.imprimirValor();

        Anime anime2 = new Anime("Boku no Hero Academia","Deku", 'M', "Irineu");
        anime2.imprimirValor();
    }

}
