/*
 * Função: Cabeçalhos e definição da classe FilaEncadeada (fila encadeada simples).
 * Contém a declaração do nó e da interface da fila.
 */
#include <iostream>
#include <string>

struct Node{
    std::string info;
    Node * prox;
};

class FilaEncadeada{
private:
    Node *inicio, *atual, *aux;

public:
    FilaEncadeada();
    void inserirValor(std::string value);
    void retirarValor();
    void imprimirLista();
};