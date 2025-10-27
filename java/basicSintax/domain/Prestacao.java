package basicSintax.domain;

public class Prestacao {

    //8) Calcular e apresentar o valor de uma prestação em atraso, utilize a fórmula abaixo:
    //*Formula*
    //Onde P é o valor da prestação em atraso.
    private double valor, taxa;
    private int meses;

    public Prestacao(double valor, double taxa, int meses){
        this.valor = valor;
        this.taxa = taxa;
        this.meses = meses;
    }

    public void calcValue(){
        double P = this.valor + (this.valor * (this.taxa / 100) * this.meses);
        System.out.println("Valor da prestação: " + P);
    }
}
