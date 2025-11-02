/*
 * Função: Multiplica cada elemento de um array por 2 e imprime os valores.
 * Entrada: ponteiro para array e tamanho.
 * Saída: impressão dos valores multiplicados no stdout.
 */
#include <stdio.h>

void multPorDois(int *arr, int tamanho){
    for (int i = 0; i < tamanho; i++){
        arr[i] *= 2;
        printf("%d", arr[i]);
        if (i < tamanho - 1) printf(", ");
    }
    printf("\n");
}

int main(void){

    int dados[] = {1, 3, 5, 7, 9};
    int tam = sizeof(dados) / sizeof(dados[0]);
    multPorDois(dados, tam);
    return 0;

}