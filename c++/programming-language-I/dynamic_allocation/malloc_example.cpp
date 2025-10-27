#include <stdio.h>
#include <stdlib.h>

int main() {
    int n;
    printf("Digite o tamanho do array: ");
    scanf("%d", &n);

    // Aloca memória para um array de 'n' inteiros
    int *arr = (int *)malloc(n * sizeof(int));
    if (arr == NULL) { // Verifica se a alocação falhou
        printf("Erro: memória insuficiente!\n");
        return 1;
    }

    // Preenche o array com valores
    for (int i = 0; i < n; i++) {
        arr[i] = i + 1;
    }

    // Exibe o array
    printf("Array alocado dinamicamente:\n");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    // Libera a memória alocada
    free(arr);

    return 0;
}
