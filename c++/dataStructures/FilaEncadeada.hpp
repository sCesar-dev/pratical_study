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