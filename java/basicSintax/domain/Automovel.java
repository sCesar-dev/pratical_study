//6) Efetuar o cálculo de quantos litros de combustível um automóvel consome em uma
    //viagem. Sabendo que o automóvel tem uma autonomia de 12 Km por litro de
    //combustível, desenvolva o cálculo utilizando a fórmula abaixo:
    //Onde:
    //D = Distância em Km
    //T = Tempo gasto na viagem
    //V = Velocidade
    
package basicSintax.domain;

public class Automovel
{
    private String name, color;
    private int T, V;

    public Automovel(String name, String color, int T, int V)
    {
        this.name = name;
        this.color = color;
        this.T = T;
        this.V = V;
    }

    public void calcEconomy()
    {
        float D = this.T * this.V;
        float costpL = D / 12;

        System.out.printf("Total de combustivel usado: %.2f", costpL);
    }
}