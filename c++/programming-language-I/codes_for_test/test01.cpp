#include <stdio.h>
#include <stdlib.h>

int main(){
    int *arr;


    arr = (int *) malloc(5 * sizeof(int));

    if (arr == NULL){
        printf("Erro na alocação da memória.");
        return 1;
    }

    for(int i = 0; i < 5; i++){
        printf("Digite o %d número: ", i+1);
        scanf("%d", arr+i);
    }

    for(int i = 0; i < 5; i++){
        printf("%d, ", *(arr+i));
    }
    free(arr);

    return 0;
}