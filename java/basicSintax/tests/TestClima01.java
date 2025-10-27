package basicSintax.tests;

import basicSintax.domain.ClimaConverter;

import java.util.Scanner;

public class TestClima01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClimaConverter clima = new ClimaConverter();

        clima.menu();
        do {
            System.out.print("Selecione uma opção: ");
            clima.op_menu = sc.nextInt();
        } while (clima.op_menu <= 0 || clima.op_menu >= 3);

        if(clima.op_menu == 1)
        {
            System.out.print("Digite o valor em F: ");
            clima.val_input = sc.nextInt();
        }

        if(clima.op_menu == 2)
        {
            System.out.print("Digite o valor em C: ");
            clima.val_input = sc.nextInt();
        }
        System.out.printf("Resultado da conversão: %.0f", clima.climaConverter());
    }
}
