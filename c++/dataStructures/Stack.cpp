#include <iostream>
using namespace std;

// ------------------------------------------------------------
// Estrutura de dados:
// - Node: nó da pilha encadeada
//     * info    -> valor armazenado
//     * proximo -> ponteiro para o próximo nó da pilha
// - Stack: classe que implementa uma pilha (LIFO) usando lista encadeada
//     * topo -> ponteiro para o topo da pilha
// ------------------------------------------------------------

struct Node
{
    int info;
    Node *proximo;
};

class Stack
{
private:
    Node *topo;

public:
    Stack();             // Construtor
    ~Stack();            // Destrutor
    void empilhar(int value);  // Push
    void desempilhar();        // Pop
    int consultarTopo();       // Retorna valor do topo sem remover
};

// ------------------------------------------------------------
// Construtor: inicializa pilha vazia
// ------------------------------------------------------------
Stack::Stack()
{
    topo = nullptr;
}

// ------------------------------------------------------------
// Destrutor: remove todos os elementos da pilha para liberar memória
// ------------------------------------------------------------
Stack::~Stack()
{
    while(topo != nullptr)
    {
        desempilhar();
    }
    cout << "Pilha apagada!";
}

// ------------------------------------------------------------
// Empilhar (Push):
// - Cria um novo nó com o valor fornecido
// - Aponta o novo nó para o topo atual
// - Atualiza o topo para o novo nó
// ------------------------------------------------------------
void Stack::empilhar(int value)
{
    Node *novo = new Node;
    novo->info = value;
    novo->proximo = topo;
    topo = novo;
}

// ------------------------------------------------------------
// Desempilhar (Pop):
// - Remove o nó do topo da pilha
// - Atualiza o topo para o próximo nó
// - Libera memória do nó removido
// - Se a pilha estiver vazia, apenas informa
// ------------------------------------------------------------
void Stack::desempilhar()
{
    if (topo != nullptr)
    {
        Node *temp = topo;
        topo = temp->proximo;
        delete temp;
    }
    else
    {
        cout << "Stack empty" << endl;
        return;
    }
}

// ------------------------------------------------------------
// Consultar topo:
// - Retorna o valor armazenado no topo da pilha sem remover
// - Se pilha vazia, retorna -1
// ------------------------------------------------------------
int Stack::consultarTopo()
{
    if (topo == nullptr)
    {
        cout << "Stack empty" << endl;
        return -1;
    }
    else
    {
        return topo->info;
    }
}

// ------------------------------------------------------------
// Função main: teste da pilha
// ------------------------------------------------------------
int main()
{
    Stack s;

    s.empilhar(5);
    s.empilhar(10);
    s.empilhar(15);

    // Mostra topo atual
    cout << "Topo atual: " << s.consultarTopo() << endl;

    s.desempilhar();
    cout << "Topo atual: " << s.consultarTopo() << endl;

    // Destrutor chamado automaticamente ao final do escopo
    // Evite chamar manualmente ~Stack()
    return 0;
}
