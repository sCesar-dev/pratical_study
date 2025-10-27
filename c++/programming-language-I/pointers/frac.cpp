#include <stdio.h>
#include <stdlib.h>

void frac(float num, int * parteinteira, int * parteFracionaria){
    *parteinteira = (int)num;
    *parteFracionaria = (int)((num-*parteinteira)*1000);
}

int main(){
    float n = 23.55;
    int result1, result2;
    frac(n, &result1, &result2);
    printf("\n%d \n%d", result1, result2);
    return 0;
}