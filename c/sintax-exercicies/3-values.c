#include <stdio.h>
#include <stdlib.h>


/*
 * Função: Calcula a média ponderada de três valores usando pesos diferentes
 *          dependendo se cada valor é maior, menor ou igual a 6.
 * Entrada: três valores reais (n1, n2, n3) informados pelo usuário.
 * Saída: impressão da média ponderada no stdout.
 */
int main(){

    float n1, n2, n3, media, soma;

    printf("\nDigite o primeiro valor: ");
    scanf("%f", &n1);
    while(n1 < 0 || n1 > 10){
        printf("\nNúmero inválido, digite novamente: ");
        scanf("%f", &n1);

    }

    if(n1 > 6){
        n1 = n1 * 5;
        soma += n1;

    }else{
        if(n1 < 6){
            n1 = n1 * 2.5;
            soma += n1;

        }else{
            n1 = n1 * 3.5;
            soma += n1;

        }
    }

    printf("\nDigite o segundo valor: ");
    scanf("%f", &n2);
    while(n2 < 0 || n2 > 10){
        printf("\nNúmero inválido, digite novamente: ");
        scanf("%f", &n2);

    }

    if(n2 > 6){
        n2 = n2 * 5;
        soma += n2;

    }else{
        if(n2 < 6){
            n2 = n2 * 2.5;
            soma += n2;

        }else{
            n2 = n2 * 3.5;
            soma += n2;

        }
    }

    printf("\nDigite o terceiro valor: ");
    scanf("%f", &n3);
    while(n3 < 0 || n3 > 10){
        printf("\nNúmero inválido, digite novamente: ");
        scanf("%f", &n3);
    }
    
    if(n3 > 6){
        n3 = n3 * 5;
        soma += n3;

    }else{
        if(n1 < 6){
            n3 = n3 * 2.5;
            soma += n1;

        }else{
            n3 = n3 * 3.5;
            soma += n3;

        }
    }

    media = soma / 3;
    printf("\nMédia ponderada dos 3 valores : %.2f", media);

}
