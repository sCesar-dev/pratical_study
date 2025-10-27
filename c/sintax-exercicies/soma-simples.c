#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num, valorFinal;
    valorFinal = 1;
    printf("Digite um numero: ");
    scanf("%d", &num);

    while (num < 0){
        printf("\nNao e possivel calcular numeros negativos, digite novamente um positivo: ");
        scanf("%d", num);
    }
    
    for(int i = 0; i < num; i++){
        valorFinal = valorFinal + i;
    }
    printf("\nValor da soma dos numeros anteriores de %d: %d", num, valorFinal);
}
