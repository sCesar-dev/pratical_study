#include <math.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int valor;
    float j, decimal;
    float binario[100];

    printf("\nDigite o numero de algarismos do binario: ");
    scanf("%d", &valor);

    while(valor < 0){
        printf("\nIncorreto, digite novamente: ");
        scanf("%d", &valor);
    }

    for(int i = 0; i < valor; i++){
        printf("\nDigite o algarismo(1 ou 0): ");
        scanf("%f", &binario[i]);

        while(binario[i] < 0 || binario[i] > 1){
            printf("\nIncorreto, digite novamente: ");
            scanf("%f", &binario[i]);
        }
    }


    for(int i = 0; i < valor; i++){
        if(binario[i] == 1){
            float potencia = pow(2.0, valor-j);
            decimal += potencia;
            printf("\n%f", decimal);
        }
        j++;
    }
}
