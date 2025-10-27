#include <stdio.h>
#include <stdlib.h>

int decompor(int arr[], int n, int *x){
    int i = 0, div = 2;
    arr[i] = n;
    while((arr[i] / div) != 0){
        div = 2;
        while((arr[i] % div) != 0){
            div++;
        }
        arr[i + 1] = (arr[i] / div);
        i++;
    } 
    *x = i;
    if (*x > 10){
        return 1;
    }else{
        return 0;
    }
}

int main(void){
    int num[100];
    int val = 32;
    int cont = 0;;
    decompor(num, val, &cont);

    return 0;
}