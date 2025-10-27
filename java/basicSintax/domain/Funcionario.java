package basicSintax.domain;

public class Funcionario
{
    private String name;
    private int age;
    private double rents;

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public double getrents()
    {
        return rents;
    }

    public Funcionario(String name, int age, double rents)
    {
        this.name = name;
        this.age = age;
        if (age < 0 || age > 120)
        {
            System.out.println("Idade inválida tente novamente!");
            return;
        }
        this.rents = rents;
    }

    public void imprimirDados()
    {
        System.out.printf("""
                Menu
                Nome: %s 
                Idade: %d anos
                Salario: %.2f

                """, getName(), getAge(), getrents());
    }

    public void mediaSalario(double... rents)
    {
        float total = 0;
        int cont = 0;
        for(double rent : rents)
        {
            total += rent;
            cont ++;
        }
        System.out.printf("Media dos salarios: %.2f", (total / cont));
        // Cuidado pois ao inves de criar uma var joguei a expressao no prinf direto(teste)
    }
}