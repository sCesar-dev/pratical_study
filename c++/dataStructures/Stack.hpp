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
