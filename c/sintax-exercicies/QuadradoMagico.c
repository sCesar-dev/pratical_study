#include <stdio.h>
#include <stdio.h>


int main(){
    
    int tam_matriz;
    int x,y, i;
    int before_x = 0; 
    int before_y = 0;//Ele será usado para simplifcar a volta do valor no caso da variavel estar ocupada

    do
    {
        printf("\nDigite um valor impar que dimensiona o tamanho da matriz: ");
        scanf("%d", &tam_matriz);

    } while ((tam_matriz % 2) == 0);
    
    int matriz[tam_matriz][tam_matriz];

    for (int i = 0; i < tam_matriz; i++)
    {
        for (int j = 0; j < tam_matriz; j++)
        {
            matriz[i][j] = 0;
        }
        
    }

    for( i = 1; i < (tam_matriz*tam_matriz); i++){

        if(i == 1){
            x = 0;
            y = (tam_matriz / 2);
            matriz[x][y] = 1;
            i++;
        }
        
        //Atualização para conseguir indentificar no caso do quadrado estar ocupado e jogar ele para o before
        x = x - 1;
        y = y - 1;

        if(x < 0){
            x = tam_matriz + x;
        }
        if(y < 0){
            y = tam_matriz + y;
        }
        if (matriz[x][y] != 0){
            x = before_x + 1;
            y = before_y;

            if(x < 0){
                x = tam_matriz + x;
            }
            matriz[x][y] = i;
            before_x = x;
            before_y = y;

        }else{
            matriz[x][y] = i;
            before_x = x;
            before_y = y;
        }
        
    }


    for (int i = 0; i < tam_matriz; i++)
    {
        for (int j = 0; j < tam_matriz; j++)
        {
            printf("%d ", matriz[i][j]);
        }
        printf("\n");
        
    }
    
}