package Cmetodos.domain;

public class Pessoa {

    private String nome;
    private int idade;
    private char sexo;

    public void setIdade(int idade) {
        if (idade <= 0){
            System.out.println("Idade inválida.");
            return;
        }
        this.idade = idade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        if (sexo != 'M' & sexo != 'F'){
            System.out.println("Obs: sexo inválido!");
            return;
        }
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void imprimirDados(){
        System.out.println("""
                \n------------------------
                Nome: """ + this.nome);
        System.out.println("Idade :" + this.idade);
        System.out.println("Sexo :" + this.sexo);
    }
}
