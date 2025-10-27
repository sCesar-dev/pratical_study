#include <stdio.h>
#include <time.h>

typedef enum { 
    JAN = 1, FEV, MAR, ABR, MAI, JUN,
    JUL, AGO, SET, OUT, NOV, DEZ 
} tipo_mes;
tipo_mes mes;

int main(){
    
    int escolha_mes, month;
    time_t now = time(NULL);
    struct tm *t = localtime(&now);

    printf("Escolha um mês(1 a 12): ");
    scanf(" %d", &escolha_mes);

    switch (escolha_mes){
    
    case 0:
        month = t-> tm_mon + 1;
        printf("Current time: %d", month);
        
    case JAN:
        printf("Janeiro");
        break;
    case FEV:
        printf("Fevereiro");
        break;
    case MAR:
        printf("Março");
        break;
    case ABR:
        printf("Abril");
        break;
    case MAI:
        printf("Maio");
        break;
    case JUN:
        printf("Junho");
        break;
    case JUL:
        printf("Julho");
        break;
    case AGO:
        printf("Agosto");
        break;
    case SET:
        printf("Setembro");
        break;
    case OUT:
        printf("Outubro");
        break;
    case NOV:
        printf("Novembro");
        break;
    case DEZ:
        printf("Dezembro");
        break;   
    default:
        printf("Entrada inválida.");
        break;
    }
}