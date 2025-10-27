#include <stdio.h>

//3- Dado um array de caracteres char mensagem[] = "Olá";, use um ponteiro para imprimir cada caractere da string.

int main(void){

    char mensagem[] = "Ola";
    char * pMsg = mensagem;

    for (int i = 0; pMsg[i] != '\0'; i++){
        //printf("%c ,". pMsg[i]);
        printf("%c ,", *pMsg++);
    }

}