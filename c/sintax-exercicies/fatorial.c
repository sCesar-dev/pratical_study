#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num, valorFinal;
    valorFinal = 1;
    printf("Digite um numero: ");
    scanf("%d", &num);

    while (num < 0){
        printf("\nNao e possivel calcular o fatorial de um numero negativo, digite novamente: ");
        scanf("%d", num);
    }
    
    for(int i = 1;i <= num;){
        valorFinal = valorFinal * i;
        i++;
    }
    printf("\nValor da fatorial de %d: %d", num, valorFinal);
    return 0;
}
