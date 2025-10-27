package Cmetodos.domain;

public class Student {
    public String name;
    public int age;
    public char sex;

    public void imprime(){
        System.out.println("------------------------\n");
        System.out.println("\nNome: " + this.name +
                "\nIdade: " + this.age +
                "\nSex: " + this.sex);
    }
}
