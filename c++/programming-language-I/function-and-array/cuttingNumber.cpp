#include <stdio.h>

//separe os valorores e imprima o inteiro ex: '3789' = '3, 7, 8, 9'

int main(void){
	int array[5000], valor, cont = 0;

    printf("Digite um numero: ");
    scanf("%d", &valor);

    while (valor > 0){
		
		array[cont] = valor % 10;
        valor /= 10;
		cont++;
		
	}

	for(int i = cont; i > 0; i--){
		printf("%d, ", array[i-1]);
	}
}