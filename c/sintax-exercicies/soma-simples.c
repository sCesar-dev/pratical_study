#include <stdio.h>
#include <stdlib.h>

/*
 * Função: Lê dois números e imprime a soma.
 * Entrada: dois valores de ponto flutuante via stdin.
 * Saída: impressão da soma no stdout.
 */
int main()
{
    float n1, n2, soma;

    printf("Digite o primeiro numero: ");
    scanf("%f", &n1);

    printf("Digite o segundo numero: ");
    scanf("%f", &n2);

    soma = n1 + n2;

    printf("\nSoma resultante: %.2f", soma);

}
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
