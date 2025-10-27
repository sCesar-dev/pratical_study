#include <iostream>
#include <string>

// ------------------------------------------------------------
// Estrutura de dados:
// - Lista encadeada simples: cada Node aponta para o próximo (prox)
// - Classe FilaEncadeada2 mantém ponteiros:
//     inicio -> início da lista
//     atual  -> último nó ou nó em iteração
// ------------------------------------------------------------

struct Node {
    int info;      // valor armazenado no nó
    Node *prox;    // ponteiro para o próximo nó
};

class FilaEncadeada2 {
private:
    Node *inicio;  // ponteiro para o início da lista
    Node *atual;   // ponteiro usado para percorrer ou inserir elementos

public:
    FilaEncadeada2();               // construtor
    void inserirElemento(int value); // insere valor mantendo ordem
};

// Construtor inicializa ponteiros como nulos
FilaEncadeada2::FilaEncadeada2() {
    atual = nullptr;
    inicio = nullptr;
}

// ------------------------------------------------------------
// Função inserirElemento:
// - Cria um novo nó e insere na lista encadeada mantendo ordem crescente.
// - Percorre a lista com o ponteiro 'atual' para encontrar a posição correta.
// - Caso a lista esteja vazia, o novo nó se torna o início.
// ------------------------------------------------------------
void FilaEncadeada2::inserirElemento(int value) {
    Node *novo = new Node();
    if (novo == nullptr) return; // verifica alocação
    novo->info = value;

    // Lista vazia
    if (inicio == nullptr) {
        inicio = novo;
        atual = novo;
        return;
    }

    // Percorre a lista para encontrar a posição de inserção
    do {
        // Inserção no início se o novo valor for menor que o primeiro
        if (novo->info < inicio->info) {
            novo->prox = inicio;
            inicio = novo;
            atual = novo;
            break;
        }

        // Inserção no meio da lista
        if (novo->info > atual->info && novo->info < atual->prox->info) {
            novo->prox = atual->prox;
            atual->prox = novo;
            atual = novo;
            break;
        }

        atual = atual->prox;
    } while (atual->prox != nullptr);

    // Inserção no final da lista
    if (atual->prox == nullptr) {
        atual->prox = novo;
        atual = novo;
    }

    // Impressão da lista (opcional)
    Node *ptr = inicio;
    while (ptr != nullptr) {
        std::cout << " " << ptr->info << std::endl;
        ptr = ptr->prox;
    }
}

int main() {
    FilaEncadeada2 fila1;
    fila1.inserirElemento(3);
    fila1.inserirElemento(2);
    fila1.inserirElemento(7);
    fila1.inserirElemento(5);
    return 0;
}
