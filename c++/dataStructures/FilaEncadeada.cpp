/*
 * Função: Implementação de métodos para uma fila encadeada simples
 * Entrada/Saída: operações de inserção e remoção de elementos em memória
 */
#include "FilaEncadeada.hpp"

FilaEncadeada::FilaEncadeada()
{
    atual = nullptr;
    inicio = nullptr;
}

void FilaEncadeada::inserirValor(std::string value)
{
    Node *novo;
    novo = new Node();
    if(novo == nullptr){return;}
    novo->info = value;
    if(atual == nullptr)
    {
        atual = novo;
        inicio = novo;
    }else{
        atual->prox = novo;
        atual = novo;
    }
}

void FilaEncadeada::retirarValor(){
    if(inicio != nullptr){
        aux = inicio;
        inicio = inicio->prox;
        delete aux;
    }else{
        std::cout <<"vazio"<<std::endl;
    }
}

int main(){
    FilaEncadeada fila1;
    fila1.inserirValor("10");
    fila1.inserirValor("20");
    fila1.inserirValor("30");
    fila1.retirarValor();
    fila1.retirarValor();
    fila1.retirarValor();
    fila1.retirarValor();
    return 0;
}