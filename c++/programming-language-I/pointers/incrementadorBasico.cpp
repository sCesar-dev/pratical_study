#include <stdio.h>

//1- If a var int cont = 5, use a pointer to increment your value at 1 and print your result.
//2- Increment each element in array numeros[5] = {2, 4, 6, 8, 10} and print them.

int main(void){

    int cont = 5;
    int * pcont = &cont;

    int numeros[5] = {2, 4, 6, 8, 10};
    int * pnum = numeros;
    

    printf("\nValue: %d\n", ++(*pcont));

    for (int i = 0; i < 5; i++){
        printf("%d ", *(pnum++)); 
    }
}