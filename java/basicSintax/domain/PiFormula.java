// package BexerciciosViradoNoJiraya.domain;

// public class PIFormula {

//     public double raio, area;
//     public void imprimirValor(){
//         area = 3.14 * (raio * raio);
//         System.out.println("Área é igual a " + this.area + "metros quadrados.");
//     }
// }

    //1)Baseado na fórmula abaixo escreva um programa que calcule e apresente a área de um
    //círculo.
    //A representa a área do círculo
    //π = 3.141592654
    //R = Raio do círculo (deve ser fornecido pelo usuário)

package basicSintax.domain;

import java.lang.Math;

public class PiFormula
{
    private double raio;

    public double getRaio()
    {
        return raio;
    }

    public void setRaio(double raio)
    {
        this.raio = raio;
    }

    public void areaCircle(double raio)
    {
        double area =  3.141592654 * (Math.pow(getRaio(), 2));
        System.out.printf("\nArea do circulo de raio %2.d: %2.d", getRaio(), area);
    }

}