#include <iostream>
#include <string>

// ------------------------------------------------------------
// Estrutura de dados:
// - Lista encadeada simples: cada Node aponta para o próximo (prox)
// - Classe FilaEncadeada1 mantém ponteiros:
//     inicio -> início da lista
//     atual  -> último nó ou nó em iteração
//     aux    -> ponteiro auxiliar usado na iteração
// ------------------------------------------------------------

struct Node {
    int info;      // valor armazenado
    Node *prox;    // ponteiro para o próximo nó
};

class FilaEncadeada1 {
private:
    Node *inicio, *atual, *aux;

public:
    FilaEncadeada1();                 // construtor
    void inserirElemento(int value);  // insere valor na lista
    bool verificaElemento(int value); // verifica se é primo
};

// Construtor inicializa ponteiros como nulos
FilaEncadeada1::FilaEncadeada1() {
    atual = nullptr;
    inicio = nullptr;
    aux = nullptr;
}

// ------------------------------------------------------------
// Função inserirElemento:
// - Insere elementos na lista encadeada seguindo regras:
//     * Se é primo e ímpar -> insere em ordem decrescente
//     * Caso contrário -> insere somente se for ímpar
// ------------------------------------------------------------
void FilaEncadeada1::inserirElemento(int value) {
    bool situacao = verificaElemento(value);

    if (situacao == true) { // é primo e ímpar
        Node *novaLista = new Node();
        if (novaLista == nullptr) return;

        novaLista->info = value;

        // Lista vazia
        if (inicio == nullptr) {
            inicio = novaLista;
            atual = novaLista;
            aux = novaLista->prox;
        } else {
            // Insere mantendo alguma ordem decrescente (lógica complexa)
            do {
                if (novaLista->info > inicio->info) {
                    novaLista->prox = inicio;                    
                    inicio = novaLista;
                    atual = novaLista;
                    break;
                } else if (novaLista->info < atual->info && novaLista->info >= atual->prox->info) {
                    novaLista->prox = atual->prox;
                    atual->prox = novaLista;
                    atual = novaLista;
                    break;
                } else if (novaLista->info < atual->info && atual->prox == aux) {
                    novaLista->prox = atual->prox;
                    atual->prox = novaLista;
                    atual = novaLista;
                    break;
                }
                atual = atual->prox;
                aux = atual;
            } while (atual->prox != nullptr);
        }

    } else {
        // Caso não seja primo
        Node *listaInicial = new Node();
        if (listaInicial == nullptr) return;

        // Apenas ímpares são adicionados
        if (listaInicial->info % 2 != 0 || listaInicial->info == 1) {
            if (inicio == nullptr) {
                inicio = listaInicial;
                atual = listaInicial;
            } else {
                listaInicial->prox = atual->prox;
                atual->prox = listaInicial;
                atual = listaInicial;
            }
        } else {
            std::cout << "Numero par, nao adicionado!" << std::endl;
        }
    }
}

// ------------------------------------------------------------
// Função verificaElemento:
// - Verifica se o valor é primo
// ------------------------------------------------------------
bool FilaEncadeada1::verificaElemento(int value) {
    if (value <= 1) return false;
    else if (value == 2) return true;
    else if (value % 2 == 0) return false;

    for (int i = 3; i <= value / 2; i += 2) {
        if (value % i == 0) return false;
    }
    return true;
}

int main() {
    FilaEncadeada1 fila1;
    fila1.inserirElemento(5);
    fila1.inserirElemento(7);
    fila1.inserirElemento(2);
    fila1.inserirElemento(3);

    return 0;
}
