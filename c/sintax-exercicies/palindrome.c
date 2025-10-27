#include <stdio.h>

int main(){
    int tot_rep, i, algarismo[10];

    printf("Digite a quantidade de algarismos: ");
    scanf("%d", &tot_rep);
    
    for(i = 0; i < tot_rep; i++){
        printf("\nDigite o %d algarismo: ", i);
        scanf("%d", &algarismo[i]);

        while(tot_rep < 1 || tot_rep > 9){
            printf("\nNumero incorreto, digite novamente: ");
            scanf("%d", &algarismo[i]);
    }
    }
    
    for(i = 0; i < tot_rep; i++){
        if(algarismo[i] != algarismo[tot_rep-i-1]){                 //vai comparar o algarismo 0 e o total selecionado pelo user,
            printf("\nNão é um palíndromo. \n%d \n%d", tot_rep, i); //subtraido por 1(ja que o primeiro termo é 0, não 1, fazendo ,
            break;                                                  //com que o ultimo seja -1(ex: 4 será {0,1,2,3})
        }else{
            if(i == tot_rep-i-1){
                printf("\nE um palíndromo");
            }
        }
    }
}
