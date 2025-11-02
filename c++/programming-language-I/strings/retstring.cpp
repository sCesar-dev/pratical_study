/*
 * Função: Conta o tamanho de uma string (meu strlen simples).
 * Entrada: string e ponteiro para inteiro que armazenará o tamanho.
 * Saída: preenche *tam com o comprimento da string e retorna o tamanho.
 */

#include <stdio.h>
#include <stdlib.h>

size_t meuStrlen(const char *str, int *tam){
    int contador = 0;
    while (str[contador] != '\0'){
        contador++;
    }
    if (tam) *tam = contador;
    return (size_t)contador;
}

int main(){
    char arr[100] = "São Salvador";
    int tamanho = 0;
    meuStrlen(arr, &tamanho);
    printf("\n%d\n", tamanho);
    return 0;
}