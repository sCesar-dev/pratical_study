#include <stdio.h>
#include <stdlib.h>

void arrAloq(int n, char *arr) {
    arr = (char *) malloc(n * sizeof(char));

    if (*arr == NULL) {
        printf("Erro na alocação de memória.");
        return;
    }
}

int main() {

    int num, i = 0;
    char *arr;

    printf("Tam str: ");
    scanf("%d", &num);

    arrAloq(num + 1, &arr); // +1 para garantir espaço para o caractere '\0'

    printf("Conteudo: ");
    fgets(arr, num + 1, stdin); // Lê até num caracteres ou até nova linha, inclui o '\0'

    // Remove o '\n' deixado por fgets
    // if (*(arr + strlen(arr) - 1) == '\n') {
    //     *(arr + strlen(arr) - 1) = '\0';
    // }

    // Substitui as vogais por espaços
    while (*(arr + i) != '\n') 
    {
        if (*(arr + i) == 'a' || *(arr + i) == 'e' || *(arr + i) == 'i' || *(arr + i) == 'o' || *(arr + i) == 'u' ||
            *(arr + i) == 'A' || *(arr + i) == 'E' || *(arr + i) == 'I' || *(arr + i) == 'O' || *(arr + i) == 'U') 
        {
            *(arr + i) = ' ';
        }
        i++;
    }

    printf("Palavra sem as vogais: %s\n", arr);

    free(arr);
    return 0;
}
