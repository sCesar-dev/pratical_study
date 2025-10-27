#include <stdio.h>
#include <stdlib.h>

typedef struct {
    double saldo_poupanca;
    double saldo_corrente;
} Contas;

void atualizar_saldo(Contas * contas, char tipo_conta, double valor){
    switch (tipo_conta){
        case 'P':
            contas->saldo_poupanca += valor;
            break;

        case 'C':
            contas->saldo_corrente += valor;
            break;

        default:
            printf("Undefined tipo_conta, try again.");
            break;
    }
}

int main(){

    Contas minhas_contas = {1000.0, 500.0};

    atualizar_saldo(&minhas_contas, 'C', -200.0);
    atualizar_saldo(&minhas_contas, 'P', +200.0);
    printf("Saldo atual Poupança: %.2f \nSaldo Atual Corrente: %.2f", minhas_contas.saldo_poupanca, minhas_contas.saldo_corrente);
    
    return 0;
}