#include <stdio.h>
#include <stdlib.h>

char* meuStrcpy(char *dest, const char *src){
/*
 * Função: Implementação simples de strcpy (copia string fonte para destino).
 * Entrada: ponteiro destino e ponteiro fonte.
 * Saída: retorna o ponteiro destino.
 */

    while(*src != '\0'){
        *dest = *src;
        *dest++;
        *src++;
    }
    *dest = '\0';
    return dest; // Corrected return value to return the destination pointer
}

int main(){
    char nome1[10] = "Brasil";
    char nome2[10] = {};
    meuStrcpy(nome2, nome1);
    for(int i = 0; i < 10; i++){
        printf("%c", nome2[i]);
    }
    return 0;
}
    
    
    