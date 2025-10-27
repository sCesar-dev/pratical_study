#include <stdio.h>

//4-Escreva uma função void multiplicaPorDois(int *arr, int tamanho) que multiplica cada elemento de um array por 2. No main, declare um array int dados[] = {1, 3, 5, 7, 9};
//, chame a função e imprima o array modificado.

void multPorDois(int *arr, int tamanho){
    for (int i = 0; i < tamanho; i++){
        *arr *= 2;
        // arr[i] *= 2;
        printf("%d - %d,", i, *arr++);
        // printf("%d ,", arr[i]);
    }
}

int main(void){

    int dados[] = {1, 3, 5, 7, 9};
    // int tam = sizeof(dados) / sizeof(dados[0]);
    int tam = 5;
    multPorDois(dados, tam);

}