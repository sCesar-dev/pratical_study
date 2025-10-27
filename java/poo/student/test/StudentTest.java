package Dpoo.student.test;

import Dpoo.student.domain.Student;

public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student();

        student.name = "João";
        student.age = 15;
        student.sex = 'M';

        student.imprimeStudent();
    }
}
