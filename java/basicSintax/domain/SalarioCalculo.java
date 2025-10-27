package basicSintax.domain;

public class SalarioCalculo {
    //2)Baseado nas fórmulas abaixo, desenvolva uma rotina que calcule o salário líquido de
    //um funcionário. Após o cálculo o programa deve apresentar o salário base e o salário
    //líquido calculado.
    //*Formula*
    //*Formula*
    //*Formula*
    //Onde:
    //SB = Salário Base
    //HT = Horas Trabalhadas
    //VH = Valor Hora de trabalho
    //TD = Total de Descontos
    //PD = Percentual de Desconto
    //SL = Salário Líquido
    public int HT, PD;
    public double VH, SL;

    public void calcularSalario(){
        double SB = HT * VH;
        double TD = ((double) PD / 100) * SB;
        SL = SB - TD;
    }
}
