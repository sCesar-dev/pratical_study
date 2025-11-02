/*
 * Função: Coleta registros com nome, sexo e altura e calcula estatísticas (maior, menor e médias).
 * Entrada: várias entradas do usuário (até 50) com nome, sexo (F/M) e altura.
 * Saída: imprime maior/menor altura e médias.
 */
#include <stdio.h>
#include <stdlib.h>

int main(){

    int cont;
    char nome[31];
    char sexo, op;
    double altura, maior_altura, menor_altura, media_altura_mulher, media_altura;

    for( cont = 1; cont < 51; cont++){
        
        fflush(stdin);
        
        printf("Digite o nome(max 30 caracteres): ");
        /*fgets(nome, sizeof(nome), stdin);*/
        scanf(" %s", nome);
        setbuf(stdin, NULL);                //limpar o buffer do nome, pois quando o la\u00e7o repetia, ele adicionava o ultimo termo
        
        printf("\nDigite a altura(max 2.5m): ");
        scanf(" %lf", &altura);

        printf("%lf", altura);
        while(altura < 0.5 && altura > 2.5){
            printf("\nAltura incorreta. Digite novamente (entre 0.5 e 2.5 metros): ");
            scanf("%lf", &altura);
        }

        fflush(stdin); //Limpar o buffer do teclado antes de receber o char para a linha 20 nao sair no prox scanf

        printf("\nDigite o sexo(F - Feminino | M - Masculino): ");
        scanf(" %c", &sexo);

        while(sexo != 'F' && sexo != 'M'){
            fflush(stdin);

            printf("\nIncorreto, digite novamente(F - Feminino | M - Masculino): ");
            sexo = getchar();
        }

        if(cont == 1){
            maior_altura = altura;
        }else{
            if (altura > maior_altura){
                maior_altura = altura;
            }
        }

        if(cont == 1){
            menor_altura = altura;
        }else{
            if (altura < menor_altura){
                menor_altura = altura;
            }
        }

        if(sexo == 'F')
            media_altura_mulher += altura;

        media_altura += altura;
        
        fflush(stdin);

        printf("\nDeseja continuar(S ou N): ");
        scanf(" %c", &op);

        if (op == 'N'){
            break;
        }
    }

    media_altura = media_altura / cont+1;
    printf("\nMaior altura: %.2f \nMenor altura: %.2f \nMedia da altura das mulheres: %.2f \nMedia da altura da turma: %.2f \n", maior_altura, menor_altura, media_altura_mulher, media_altura);

    return 0;
}