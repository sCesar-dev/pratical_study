#include <stdio.h>
#include <stdlib.h>
/*
 * Função: Conta o comprimento do maior segmento crescente em uma sequência de inteiros.
 * Entrada: sequência de inteiros informada pelo usuário (termina quando o usuário indicar 'N').
 * Saída: imprime o comprimento do maior segmento crescente.
 */
#include <stdio.h>
#include <stdlib.h>

int main(){

    int cont, cont_max;                                  //cont para contar o numero de segmentos crescentes. cont_max para registrar o cont em uma variavel de maximos.
    int numero, numero_max, repetidor;
    char op;
    
    cont = 0;
    repetidor = 0;
    cont_max = 0;
    numero_max = 0;

    printf("Informe o primeiro numero da sequencia: ");
    scanf("%d", &numero);

    while (repetidor == 0){
        
        if(numero > numero_max){
            numero_max = numero;
            cont++;                                         //contador acrescenta +1
        }else{
            if(cont == 0){                                  //lembrando que toda variavel tipo int inicializasse em 0 quado nao e atribuido valor, se cont for 0 e ele adicionar 0, 0 sera considerado a posicao do segmento e contabilizado
                numero_max = numero;
                cont++;
            }
            if (numero < numero_max && cont_max <= cont){                      //com numero menor que o maximo crescente, o contador encerra e adiciona o cont somado ao cont_max
                cont_max = cont;
                cont = 0;                                                     //na mesma condicional ele verifica se esse cont somado \u00e9 maior que o cont j\u00e1 registrado(cont_max)
                numero = 0;
                numero_max = 0;
            }                                                                        //Isso foi feito pois mesmo com o numero crescente o usuario podera criar outro seguimento(afinal, o codigo so encerra com o NAO dele),
        }                                                                            //Assim, resumindo, o numero max que vai sinalizar o segmento crescente de comprimento maximo
        
        printf("Deseja continuar(S - SIM | N - NAO): ");
        scanf(" %c", &op);

        if(op == 'N'){
            break;
        }

        printf("Digite o proximo numero: ");
        scanf("%d", &numero);

    }

    if(cont_max == 1){
        printf("Com apenas um ponto, nao possui seguimento crescente de comprimento maximo.");
    }

    printf("Comprimento do segmento crescente de comprimento m\u00e1ximo: %d", cont_max);
    
}
