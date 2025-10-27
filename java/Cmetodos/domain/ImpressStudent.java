package Cmetodos.domain;

public class ImpressStudent {
    public void impressStudent(Student student){
        System.out.println("------------------------\n");
        System.out.println("\nNome: " + student.name +
                "\nIdade: " + student.age +
                "\nSex: " + student.sex);
    }
}
