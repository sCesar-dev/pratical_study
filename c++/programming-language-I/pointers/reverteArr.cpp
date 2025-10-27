#include <stdio.h>

//5- Escreva uma função void reverte(int *arr, int tamanho) que inverte um array. Use-a em int valores[] = {1, 2, 3, 4, 5}; e imprima o array invertido.
void reverte(int *rev_arr, int rev_tam){
    int arr_reverted[rev_tam]; //tive que criar uma segunda array para guardar os dados invertidos
    for(int i = 0; i < rev_tam;i++){
        arr_reverted[i] = rev_arr[rev_tam-i-1];
        printf("\n%d", arr_reverted[i]);
    }
}

int valores[]{1, 2, 3, 4, 5};
int tamanho = sizeof(valores) / sizeof(valores[0]);

int main(void){
    
    //5-
    reverte(valores, tamanho);
}