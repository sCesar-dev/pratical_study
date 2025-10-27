package Dpoo.constructors.Domain;

public class Anime {
    private String nomeAnime, estudio;
    private String nomePersonagem;
    private char sexo;

    public String getNomeAnime(){
        return this.nomeAnime;
    }

    public String getNomePersonagem(){
        return this.nomePersonagem;
    }

    public char getSexo(){
        return this.sexo;
    }

    public String getEstudio(){
        return this.estudio;
    }


    //Construtor
    public Anime(String nomeAnime, String nomePersonagem, char sexo){
        this.nomeAnime = nomeAnime;
        this.nomePersonagem = nomePersonagem;
        this.sexo = sexo;
    }
    //Sobrecarga do Construtor

    public Anime(String nomeAnime, String nomePersonagem, char sexo, String estudio){
        this(nomeAnime, nomePersonagem, sexo);
        this.estudio = estudio;
    }

    public void imprimirValor(){
        System.out.println("------------------------\nNome do Anime: " + getNomeAnime() +
                "\nNome do Personagem: " + getNomePersonagem() +
                "\nSexo do Personagem: " + getSexo() +
                "\nEstúdio: " + getEstudio());
    }
}
