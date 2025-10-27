#include <stdio.h> 

/*3. Faça o programa que apresenta a seguinte saída, perguntando ao usuário o número máximo (no exemplo, 9). Este número deve ser sempre ímpar.

1 2 3 4 5 6 7 8 9
   2 3 4 5 6 7 8
      3 4 5 6 7
         4 5 6
            5   */
            
int main(void){

	int num, arr[500], x;
	
	printf("Digite um numero impar: ");
	scanf("%d", &num);
	
	while((num % 2) == 0){
		printf("Numero par não permitido, digite novamente: ");
		scanf("%d", &num);
	}
	
	for(int j = 0; j < num; j++){
		arr[j] = j+1;		// 0-1 1-2 2-3 3-4 ... 8-9
	}
	
	for(x = 0; x <= num/2; x++){
		printf("\n");
		for(int i = 0 + x;i < num - x; i++){
			printf("%d ", arr[i]);
		}
	}
} 