#include <stdio.h>

//6 - Escreva uma função void somaElementos(int *arr, int tam) que calcula e imprime a soma dos elementos de um array de inteiros. 
//    No main, declare um array int numeros[] = {1, 2, 3, 4, 5, 6, 7};. 
//    Chame a função somaElementos passando o sub-array a partir do terceiro elemento (ou seja, &numeros[2]) e o tam adequado, para calcular a soma dos elementos a partir do terceiro elemento do array original.
void somaElementos(int *sumE_arr, int sumE_tam){
    int elemSum_value = 0;
    for (int i = 0; i < sumE_tam; i++){
         elemSum_value += *sumE_arr++;
    }
    printf("Sum element value: %d\n", elemSum_value);
}

int numbers[] = {1, 2, 3, 4, 5, 6, 7};
int * soElemptr = numbers; 
int tam = sizeof(numbers) / sizeof(numbers[0]);

int main(void){
    somaElementos(soElemptr + 2, tam - 2);
}