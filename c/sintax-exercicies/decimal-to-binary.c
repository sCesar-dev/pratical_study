/*
 * Função: Converte um número decimal para sua representação binária.
 * Entrada: numero inteiro decimal (positivo).
 * Saída: impressão no stdout da representação binária.
 */
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int valor, resto, i, divi;
    int binario[8];
    
    divi = 2;
    i = 0;

    printf("\nDigite o numero em decimal a ser convertido em binario: ");
    scanf("%d", &valor);

    while(valor < 0){
        printf("\nIncorreto, digite novamente: ");
        scanf("%d", &valor);
    }

    while(valor != 0){              //durante o ciclo de divisao o resto sera 0, fazendo com que o looping finalize

        resto = valor % divi;
        valor = valor / divi;

        binario[i] = resto; 
        i++;

    }

    for ( i = i-1;i >= 0; i--){     //ele imprime a entrada na ordem decrescente, seguindo padrao de representação do binario
        printf("%d", binario[i]);
    }
}
