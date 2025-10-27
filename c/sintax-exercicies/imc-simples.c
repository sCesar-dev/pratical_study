#include <stdio.h>

int main(){

    int tempo, i, segundo, minuto, hora, cont;
    double peso, pesoInicial;
    
    printf("Digite o peso em gramas do produto: ");
    scanf("%lf", &peso);

    pesoInicial = peso;
    segundo = 0;
    minuto = 0;
    hora = 0;

    while(peso > 0.5){
        peso = peso / 2;

        segundo += 50;
        if(segundo > 60 ){
            segundo -= 60;
            minuto++;

            if(minuto > 60){
                minuto -= 60;
                hora++;
            }
        }
    }

    printf("\nMassa inicial: %.2lf \nMassa final: %.2lf \nTempo(Hr, min, seg): %d h, %d min, %d s", pesoInicial, peso, hora, minuto, segundo);

} 
