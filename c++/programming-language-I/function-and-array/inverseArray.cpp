#include <stdio.h>

int inverteArr(int arr[], int tam){
    int x, array[500];

    for(x = 0; x < tam; x++){
        array[x] = arr[x];
    }
    for(x = 0; x < tam; x++){
        arr[x] = array[tam - 1 - x];
        printf("%d ,", arr[x]);
    }
    
}

int main(void){
    int array2[] = {1,2,3,4,5,6,7,8,9};
    int tamanho = sizeof(array2) / sizeof(int);
    inverteArr(array2, tamanho);
    // for(int x = 0; x < tamanho; x++){
    //     printf("%d ,", inverteArr(array2, tamanho));
    // }
}