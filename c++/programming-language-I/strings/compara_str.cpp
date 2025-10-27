#include <stdio.h>
#include <stdlib.h>

void verificaString(char * arr1, char * arr2){
    while ((*arr2) != '\0'){
        while ((*arr1) != '\0'){
            if(*arr2 == *arr1){
                *arr2++;
                break;
            }else{
                *arr1++;
            }
        }
        if ((*arr1) == '\0') {
            printf("false");
            break;
        }
    }
    if((*arr2) == '\0'){
        printf("true");
    }
}

int main(void){
    char nome1[60] = "abc";
    char nome2[60] = "abcdef";
    verificaString(nome1, nome2);
    return 0;
}