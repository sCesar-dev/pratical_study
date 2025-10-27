package Dpoo.student.domain;

public class Teacher {
    public String name;
    public int age;
    public char sex;

    public void impressTeacher(){
        System.out.println("Nome: " + name +
                "\nIdade: " + age +
                "\nSexo: " + sex);
    }
}
