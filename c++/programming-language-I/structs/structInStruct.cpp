#include <stdio.h>

typedef struct{
    int base, altura;
} Area;

typedef struct{
    char nome[50 + 1];
    int num_lado;
    Area area_objeto;
} Poligono;

int main(void){

    Poligono poligono1 = (Poligono){
        .nome = "Pentagono",
        .num_lado = 5,
        .area_objeto = (Area){
            .base = 10,
            .altura = 5
        }
    };

    printf("Nome: %s \nNumero de lados: %d \nArea do objeto: %d", poligono1.nome, poligono1.num_lado, (poligono1.area_objeto.altura * poligono1.area_objeto.base));
}