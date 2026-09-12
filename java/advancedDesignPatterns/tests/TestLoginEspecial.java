package advancedDesignPatterns.tests;

import advancedDesignPatterns.domain.LoginEspecial;

public class TestLoginEspecial {
    public static void main(String[] args) {
        LoginEspecial loginEspecial = new ("fulano","123","alguem");
        System.out.println(loginEspecial.getDica());
    }
}
