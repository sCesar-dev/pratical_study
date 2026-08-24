package advancedDesignPatterns.tests;

import advancedDesignPatterns.domain.Login;

public class TestLogin {
    public static void main(String[] args) {
        Login login = new Login("eduardo", "123");
        System.out.println(login.verificaLogin("carlos", "123"));
    }
}