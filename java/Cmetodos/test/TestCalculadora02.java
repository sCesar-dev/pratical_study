package Cmetodos.test;

import Cmetodos.domain.Calculadora;

import java.util.Scanner;

public class TestCalculadora02 {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número: ");
        int n1 = sc.nextInt();
        System.out.println("Digite o número: ");
        int n2 = sc.nextInt();

        calculadora.SubtraiNumeros(n1, n2);
        calculadora.MultiplicaNumeros(n1, n2);
        double result = calculadora.DivideNumeros(n1, n2);
        System.out.println(result);
    }

}
