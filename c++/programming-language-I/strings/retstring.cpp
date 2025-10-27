#include <stdio.h>
#include <stdlib.h>

size_t meuStrlen(const char *str, int *tam){
    while(*str != '\0'){
        (*tam)++;
        *str++;
    }
    return 0;
}

int main(){
    char arr[100] = "São Salvador";
    int tamanho = 0;
    meuStrlen(arr, &tamanho);
    printf("\n%d", tamanho);
}