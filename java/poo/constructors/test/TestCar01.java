package Dpoo.constructors.test;

import Dpoo.constructors.Domain.Car;

import java.util.Scanner;

public class TestCar01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a distância a ser percorrida pelos carros seguintes: ");
        int vel = sc.nextInt();
        Car.setVelocidadePercorrida(vel);

        Car car1 = new Car("Mercedes", 250);
        Car car2 = new Car("Mc Lareen", 270);
        Car car3 = new Car("Ferrari", 300);
        Car[] carros = {car1,car2, car3};

        for (Car car: carros ) {
            car.calcularTempoPercorrido();
        }


    }
}