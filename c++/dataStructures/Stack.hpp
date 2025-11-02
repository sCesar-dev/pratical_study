/*
 * Função: Declaração de uma pilha (Stack) usando lista encadeada.
 * Contém a definição do nó e a interface pública da pilha (push/pop/top).
 */
#include <iostream>
#include <string>

struct Node{
    std::string info;
    struct Node *prox;
};

class Stack{
private:
    Node *topo;

public:
    Stack();
    void empilhar(std::string value);
    void desempilhar();
    void consultarTopo();
};
