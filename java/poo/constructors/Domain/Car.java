package Dpoo.constructors.Domain;

public class Car {

    private final String nomeCarro;
    private final int velocidadeAtual;
    private static int velocidadePercorrida;

    public Car(String nomeCarro, int velocidadeAtual){
        this.nomeCarro = nomeCarro;
        this.velocidadeAtual = velocidadeAtual;
    }

    public void calcularTempoPercorrido(){
        double tempo = (double) this.velocidadeAtual / Car.velocidadePercorrida;
        System.out.println("----------------------\nNome do carro: " + this.nomeCarro);
        System.out.println("Velocidade do carro: " + this.velocidadeAtual);
        System.out.println("Tempo: " + tempo + " h");
    }

    public static void setVelocidadePercorrida(int velocidadePercorrida){
        Car.velocidadePercorrida = velocidadePercorrida;
    }

}
