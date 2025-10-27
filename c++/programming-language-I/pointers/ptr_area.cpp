#include <stdio.h>
#include <stdlib.h>

void calcEsfera(float R, float *area, float *volume){
    *area = 4 * 3.14 * (R*R);
    *volume = (4 * 3.14 * (R*R*R))/3;
    printf("Area: %.2f", *area);
    printf("Volume: %.2f", *volume);
}

int main(void){
    float raio = 20.0, area_esfera = 0.0, vol_esfera = 0.0;
    calcEsfera(raio, &area_esfera, &vol_esfera);
}