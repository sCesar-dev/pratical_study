#include <iostream>
#include <string>

struct Nodo2
{
    std::string info;
    struct Nodo2 *ant, *prox;
};

class ListaDupla
{
private:
    Nodo2 *inicio, *atual, *aux;

public:
    ListaDupla()
    {
        inicio = nullptr;
        atual = nullptr;
    }
    
    void imprimeLista();
    void editarPalavra(std::string palavra);
    void inserirPalavra(std::string palavra);
    void eliminarPalavra(std::string palavra);
    void palavraAnterior();
    void palavraPosterior();
    void sair();
};