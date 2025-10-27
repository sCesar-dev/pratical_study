package basicSintax.domain;

public class VolumeLata {
    //5) Escreva uma rotina capaz de calcular e apresentar o valor do volume de uma lata de
    //óleo, utilize a fórmula abaixo.
    //*Formula*
    //2
    //*Formula*
    //Onde:
    //V = Volume
    //π= 3.141592654
    //R = Raio da circunferência da lata
    //A = Altura da lata
    //3
    //OBS: O volume deve ser apresentado com a unidade de medida correto  .

    private double pi, R, A;
    public VolumeLata(Double pi, double R, double A){
        this.pi = pi;
        this.R = R;
        this.A = A;
    }

    public void calcularValor(){
        double volume = (this.pi * (this.R * this.R) * this.A);
        System.out.println("Volume da lata: " + volume + " cm3");
    }
}