package Cmetodos.test;

import Cmetodos.domain.Student;

public class TestStudent02 {
    public static void main(String[] args) {
        Student student1 = new Student();

        student1.name = "Irineu";
        student1.age = 15;
        student1.sex = 'M';

        Student student2 = new Student();

        student2.name = "Maria";
        student2.age = 17;
        student2.sex = 'F';

        student1.imprime();
        student2.imprime();
    }
}
