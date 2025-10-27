#include <stdio.h>
#include <stdlib.h>

int main(){

    int num, min_val, max_val;
    float sum_val = 0.0;
    int *arr;

    printf("Qtd num digitar: ");
    scanf("%d", &num);

    arr = (int *) malloc(num * sizeof(int));

    if(arr == NULL){
        printf("Erro na alocação da memória.");
        return 1;
    }
    
    for(int i = 0; i < num; i++){
        printf("%d - ", i+1);
        scanf("%d", arr+i);
        if(i == 0){
            min_val = *arr;
            max_val = *arr;
        }else{
            if(*(arr+i) > max_val) max_val = *(arr+i);
            if(*(arr+i) < min_val) min_val = *(arr+i);
        }
        sum_val += *(arr+i);
    }

    printf("Maior: %d\nMenor: %d\nMedia: %.2f", max_val, min_val, sum_val/num);

    free(arr);
    return 0;
}