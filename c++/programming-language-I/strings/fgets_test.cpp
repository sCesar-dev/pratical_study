#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
 * Função: Teste de leitura de string do stdin (fgets/scanf).
 * Entrada: string via stdin.
 * Saída: imprime a string lida.
 */

int main(void){
    // char nome[30];
    // printf("Digite seu nome: ");
    // fgets(nome, 30, stdin);
    // fputs(nome, stdout);
    char nome[30] = "Samuel Cesar";
    printf("Digite seu nome: ");
    scanf("%c", nome);
    printf("%s", nome);
}