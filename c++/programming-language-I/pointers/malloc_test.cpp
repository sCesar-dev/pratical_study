#include <stdio.h>
#include <stdlib.h> // malloc & free

int main()
{
    int *ptr;
    int tamanho;

    printf("Digite o tamanho do array: ");
    scanf("%d", &tamanho);

    // Agora podemos alocar memória dinamicamente
    // com o tamanho correto. Observe que
    // o espaço de memória é referenciado por um ptr
    ptr = (int *)malloc(tamanho * sizeof(int));

    // Para toda alocação de memória utilizando
    // malloc temos que verificar se houve sucesso
    if (ptr == NULL) {
        printf("Erro na alocação de memória.\n");
        return 1;
    }

    // Preenche e imprime o array
    for (int i = 0; i < tamanho; i++) {
        ptr[i] = i + 1;
        printf("%d ", ptr[i]);
    }

    free(ptr); // liberando memória
}