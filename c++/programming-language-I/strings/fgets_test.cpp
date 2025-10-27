#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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