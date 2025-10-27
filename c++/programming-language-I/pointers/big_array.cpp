#include <stdio.h>
#include <stdlib.h>

void high_number(int *arr, int *tam, int *numhighptr, int *cont_numptr){
    for(int j = 0; j < *tam; j++){
        if (j == 0){
            *numhighptr = *arr;
        }
        if (*(arr+1) > *numhighptr){
            *numhighptr = *(arr+1);
            *cont_numptr = 1;
        }else if(*(arr+1) == *numhighptr){
            (*cont_numptr)++;
        }
        *arr++;
    }
    printf("\n%d \n%d", *numhighptr, *cont_numptr);
}

int main(){
    int numeros[] = {5, 2, 15, 3, 7, 15, 8, 6, 15};
    int tamanho = sizeof(numeros) / sizeof(int);
    int num_maior = 0, cont_num_maior = 1;
    high_number(numeros, &tamanho, &num_maior, &cont_num_maior);
}