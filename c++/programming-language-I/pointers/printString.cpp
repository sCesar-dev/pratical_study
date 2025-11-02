/*
 * Função: Imprime cada caractere de uma string usando um ponteiro.
 * Entrada: string embutida no código.
 * Saída: impressão dos caracteres no stdout.
 */
#include <stdio.h>

int main(void){

    char mensagem[] = "Ola";
    char * pMsg = mensagem;

    for (; *pMsg != '\0'; pMsg++){
        printf("%c", *pMsg);
        if (*(pMsg+1) != '\0') printf(", ");
    }
    printf("\n");
    return 0;

}