package Cmetodos.test;

import Cmetodos.domain.Calculadora;

public class TestCalculadora04 {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        int[] numeros = {1,2,3,4,5,6};
        calculadora.somaArray(numeros); // Da forma normal

        calculadora.somaVarArgs(numeros); // varargs fazendo a mesma coisa que a de cima

        calculadora.somaVarArgs(1,2,3,4,5,6,7); // varargs consegue manipular no parametro os valores
    }
}
