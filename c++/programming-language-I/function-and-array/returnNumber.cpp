
//retorne o numero repetido mais vezes na array [1,1,3,3,3]

#include <stdio.h>

int rep(int arr[], int tam){

    int result;
    int contMaior = 0, contActual = 1;

    for(int i = 0; i < tam; i++){
        for(int j = 0+1; j < tam; j++){

            if (arr[i] == arr[j]){
                contActual++;
                if (contMaior <  contActual){
                    result = arr[i];
                    contMaior = contActual;
                    contActual = 1;
                }
            }
        }
    }

    return result;
}

int main(void){

    int array[] = {3, 1, 3, 1, 3, 4 , 1, 1};
    int tam = sizeof(array)/sizeof(int);

    printf("%d", rep(array, tam));
}