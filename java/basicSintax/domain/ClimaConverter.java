//3) Escreva um programa que realize a conversão de graus Celsius (C) para graus
//Fahrenheit (F). Utilize a fórmula abaixo:
//4) Escreva um programa que realize a conversão de graus Fahrenheit (F) para graus
//Celsius (C). Utilize a fórmula abaixo:

package basicSintax.domain;

public class ClimaConverter
{

    public int op_menu, val_input;
    public float val_output;

    public void menu()
    {
        System.out.println("""
                Menu
                1 - Converter F para C
                2 - Converter C para F
                3 - Exit

                """);
    }

    public float climaConverter()
    {
        while (op_menu > 0 || op_menu < 3)
        {
            if (op_menu == 1)
            {
                val_output = ((val_input - 32) * 5) / 9;
                break;
            }

            if (op_menu == 2)
            {
                val_output = ((9 * val_input) / 5) + 32;
                break;
            }
        }

        return val_output;
    }
}