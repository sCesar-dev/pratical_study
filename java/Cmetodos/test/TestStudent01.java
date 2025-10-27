package Cmetodos.test;

import Cmetodos.domain.ImpressStudent;
import Cmetodos.domain.Student;

public class TestStudent01 {
    public static void main(String[] args) {
        ImpressStudent impressora = new ImpressStudent();
        Student student1 = new Student();

        student1.name = "Irineu";
        student1.age = 15;
        student1.sex = 'M';

        Student student2 = new Student();

        student2.name = "Maria";
        student2.age = 17;
        student2.sex = 'F';

        impressora.impressStudent(student1);
        impressora.impressStudent(student2);
    }
}
