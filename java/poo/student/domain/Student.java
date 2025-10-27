package Dpoo.student.domain;

public class Student {
    public String name;
    public int age;
    public char sex;

    public void imprimeStudent(){
        System.out.println("Nome: " + name +
                "\nIdade: " + age +
                "\nSex: " + sex);
    }
}
