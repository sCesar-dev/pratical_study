package basicSintax.tests;

import java.util.Scanner;

import basicSintax.domain.SalarioCalculo;

public class SalarioTest01 {
    public static void main(String[] args) {
        SalarioCalculo salario = new SalarioCalculo();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de horas trabalhadas: ");
        salario.HT = sc.nextInt();
        System.out.println("Digite o percentual de desconto: ");
        salario.PD = sc.nextInt();
        System.out.println("Digite o valor por hora: ");
        salario.VH = sc.nextDouble();

        salario.calcularSalario();
        System.out.println("Valor líquido: " + salario.SL);
    }
}