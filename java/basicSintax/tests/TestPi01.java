package basicSintax.tests;

import BexerciciosViradoNoJiraya.domain.PIFormula;

import java.util.Scanner;

public class TestPi01 {
    public static void main(String[] args) {
        PIFormula pi = new PIFormula();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor do raio: ");
        pi.raio = sc.nextInt();
        pi.imprimirValor();
    }
}
