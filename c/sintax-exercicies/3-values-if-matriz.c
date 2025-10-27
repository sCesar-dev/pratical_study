#include <stdio.h>

/*2 - Escrever um algoritmo que lê um conjunto de 4 valores i,a,b,c,
onde i é um valor inteiro e positivo e a,b,c são quaisquer valores 
reais e os escreva. A seguir:
Se i=1 escrever os 3 valores a, b,c em ordem crescente.
Se i=2 escrever os 3 valores a,b,c em ordem decrescente.
Se i=3 escrever os 3 valores de forma que o maior valor entre a,b,c fica entre os outros
*/

int main(){

    int op, a, b, c;
    int resultado[3];

    printf("\n1. Crescente");
    printf("\n2. Decrescente");
    printf("\n3. Maior valor no meio.");
    printf("\nSelecione uma opção: ");
    scanf("%d", &op);    
    
    while (op < 1 || op > 3){
        printf("Valor incorreto, digite novamente: ");
        scanf("%d", &op);
    }

    printf("Digite o valor a: ");
    scanf("%d", &a);
    printf("Digite o valor b: ");
    scanf("%d", &b);
    printf("Digite o valor c: ");
    scanf("%d", &c);

    
    if (a < b && a < c){
        resultado[0] = a;           //a é o menor dos termos
        if (b < c){                 //ele avalia se b ou c é o menor
             resultado[1] = b;
            resultado[2] = c;
        }else{
            resultado[1] = c;
            resultado[2] = b;
        }
    }else{                          //se a não é o menor, ele avalia outras possibilidades
        if (a < b && a > c){        //se b é maior que a e c é menor que a, logo c é menor que b
            resultado[0] = c;
            resultado[1] = a;
            resultado[2] = b;
        }else{
            if(a > b && a < c){     //eu adicionei esse if pois ainda existe a possibilidade do a ser o maior de todos
                resultado[0] = b;
                resultado[1] = a;
                resultado[2] = c;
            }else{
                resultado[2] = a;
                if (b < c){
                    resultado[0] = b;
                    resultado[1] = c;
                }else{
                    resultado[0] = c;
                    resultado[1] = b;
                }
                
            }
            
        }
    }
    switch (op){

        // O motivo de eu ter feito em vetores foi para usar uma unica condicional(crescente) e somente mudar a ordem de saída dos valores.

    case 1:
        for(int i = 0; i < 3; i++){
            printf("\nResultado: %d", resultado[i]);
        }
        break;
    
    case 2:
        for(int i = 2; i >= 0; i--){
            printf("\nResultado: %d", resultado[i]);
        }
        break;

    case 3:
        for(int i = 0; i < 2; i++){
            if(i == 1){
                printf("\nResultado: %d", resultado[2]);
            }
            if(i == 2){
                printf("\nResultado: %d", resultado[1]);
            }else{
                printf("\nResultado: %d", resultado[i]);
            }
        }
    }
}