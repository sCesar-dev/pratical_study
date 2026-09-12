package advancedDesignPatterns.domain;

import advancedDesignPatterns.domain.Login;

public class LoginEspecial extends Login {
    private String dica;

    public LoginEspecial(String nome, String senha, String dica) {
        super(nome, senha);
        this.dica = dica;
    }

    public String getDica() {
        return dica;
    }
}
