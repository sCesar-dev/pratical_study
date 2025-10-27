#include <iostream>
#include <cstdlib>     // Para exit()

// ------------------------------------------------------------
// Estruturas de dados principais:
// 1. Node -> Nó de uma Árvore Binária de Busca (BST):
//     * info -> valor armazenado
//     * esq  -> ponteiro para filho esquerdo
//     * dir  -> ponteiro para filho direito
// 2. NoPilha -> Nó de uma pilha auxiliar para armazenar ponteiros para nós da árvore
//     * noDaArvore -> referência para nó da árvore
//     * prox       -> próximo nó na pilha
// 3. Arvore -> classe que gerencia a BST
//     * raiz -> raiz da árvore
//     * topo -> topo da pilha auxiliar
//     * ac   -> contador auxiliar para alguns métodos
// ------------------------------------------------------------

struct Node{
    int info;
    Node *esq, *dir;
};

struct NoPilha{
    Node *noDaArvore;
    NoPilha *prox;
};

class Arvore{
private:
    int ac = 0;

public:
    Arvore();
    Node *raiz;
    Node* inserir(Node *r, int n);        // Inserção recursiva
    void inserirIterativo(Node *r, int n);// Inserção iterativa
    void emOrdem(Node *r);                // Percurso em ordem
    void inserirPublico(int n);           // Interface pública de inserção
    void emOrdemPublico();                // Interface pública de emOrdem
    void contarNos(Node *r);              // Contagem de nós recursiva
    void contarNosPublico();              // Interface pública de contar nós
    int alturaRecursiva(Node* r);         // Calcula altura da árvore
    void alturaRecursivaPublica();        // Interface pública
    int contarFolhas(Node*r);             // Conta folhas recursivamente
    void analiseSubArvoresFolhas(Node*r);// Compara folhas das subárvores esquerda e direita
    void analiseSubArvoresFolhasPublico();// Interface pública
    void imprimirExtremidades(Node *r);   // Imprime extremidade esquerda e direita
    bool acharAscendentes(Node *no, int val); // Imprime ascendentes de um nó
    void imprimirAscendentes(int val);          // Interface pública
    NoPilha *topo;                         // Topo da pilha auxiliar
    void push(Node *no);                   // Empilha nó na pilha auxiliar
    bool gerarPilAsc(Node *no, int val, NoPilha *pilha); // Cria pilha de ascendentes
    NoPilha *gerarPilhaAsc(int val);       // Interface pública
};

// ------------------------------------------------------------
// Construtor: inicializa raiz como nullptr
// ------------------------------------------------------------
Arvore::Arvore(){
    raiz = nullptr;
}

// ------------------------------------------------------------
// Inserção recursiva em BST
// - Nó duplicado não é permitido
// ------------------------------------------------------------
Node* Arvore::inserir(Node * r, int n){
    if(r == nullptr){
        r = new Node();
        if (r==nullptr) exit(1);
        r->info = n;
        r->esq = nullptr;
        r->dir = nullptr;
        return r;
    }
    if(n > r->info) r->dir = inserir(r->dir, n);
    else if(n < r->info) r->esq = inserir(r->esq, n);
    else std::cout << "Numero repetido" << std::endl;
    return r;
}

// Interface pública para inserção
void Arvore::inserirPublico(int n){
    raiz = inserir(raiz, n);
}

// ------------------------------------------------------------
// Percurso em ordem (esq -> raiz -> dir)
// ------------------------------------------------------------
void Arvore::emOrdem(Node * r){
    if(r == nullptr) return;
    emOrdem(r->esq);
    std::cout << r->info << " ";
    emOrdem(r->dir);
}
void Arvore::emOrdemPublico(){
    emOrdem(raiz);
}

// ------------------------------------------------------------
// Contagem de nós (recursiva)
// Observação: lógica de incremento ac está incorreta
// ------------------------------------------------------------
void Arvore::contarNos(Node * r){
    if(r == nullptr) return;
    ac++;  // incrementa para cada nó visitado
    contarNos(r->esq);
    contarNos(r->dir);
}
void Arvore::contarNosPublico(){
    ac = 0; // reset contador
    contarNos(raiz);
    std::cout << "Quantidade de nós: " << ac << std::endl;
}

// ------------------------------------------------------------
// Altura recursiva da árvore
// - Retorna -1 para árvore vazia
// - Altura = número máximo de arestas da raiz até uma folha
// ------------------------------------------------------------
int Arvore::alturaRecursiva(Node* r){
    if (r == nullptr) return -1;
    int altura_esq = alturaRecursiva(r->esq);
    int altura_dir = alturaRecursiva(r->dir);
    return 1 + ((altura_esq > altura_dir) ? altura_esq : altura_dir);
}
void Arvore::alturaRecursivaPublica(){
    std::cout << "Altura recursiva: " << alturaRecursiva(raiz) << std::endl;
}

// ------------------------------------------------------------
// Contagem de folhas
// ------------------------------------------------------------
int Arvore::contarFolhas(Node*r){
    if(r == nullptr) return 0;
    if(r->esq == nullptr && r->dir == nullptr) return 1;
    return contarFolhas(r->esq) + contarFolhas(r->dir);
}

// Análise das subárvores: qual tem mais folhas
void Arvore::analiseSubArvoresFolhas(Node*r){
    if(r == nullptr){
        std::cout << "Arvore vazia! \n";
        return;
    }
    if(r->esq == nullptr && r->dir == nullptr){
        std::cout << "Arvore somente com raiz!\n";
        return;
    }

    int folhasSubEsq = contarFolhas(r->esq);
    int folhasSubDir = contarFolhas(r->dir);

    if (folhasSubEsq > folhasSubDir) std::cout << "Esquerda tem mais folhas: " << folhasSubEsq << "\n";
    else if (folhasSubDir > folhasSubEsq) std::cout << "Direita tem mais folhas: " << folhasSubDir << "\n";
    else std::cout << "Igual número de folhas: " << folhasSubDir << "\n";
}
void Arvore::analiseSubArvoresFolhasPublico(){
    analiseSubArvoresFolhas(raiz);
}

// ------------------------------------------------------------
// Impressão das extremidades esquerda e direita
// ------------------------------------------------------------
void Arvore::imprimirExtremidades(Node *r){
    if(r == nullptr) return;

    Node *at = r;
    while(at->esq != nullptr || at->dir != nullptr){
        if(at->esq != nullptr) at = at->esq;
        else at = at->dir;
    }
    std::cout << "Extremo esquerda: " << at->info << "\n";

    at = r;
    while(at->esq != nullptr || at->dir != nullptr){
        if(at->dir != nullptr) at = at->dir;
        else at = at->esq;
    }
    std::cout << "Extremo direita: " << at->info << "\n";
}

// ------------------------------------------------------------
// Impressão de ascendentes de um nó (recursivo)
// ------------------------------------------------------------
bool Arvore::acharAscendentes(Node *no, int val){
    if(no == nullptr) return false;
    if(no->info == val) return true;

    bool encontrado = false;
    if(no->info > val) encontrado = acharAscendentes(no->esq, val);
    else encontrado = acharAscendentes(no->dir, val);

    if(encontrado) std::cout << no->info << " ";
    return encontrado;
}
void Arvore::imprimirAscendentes(int val){
    acharAscendentes(raiz, val);
}

// ------------------------------------------------------------
// Pilha auxiliar (NoPilha) usada para armazenar nós ascendentes
// ------------------------------------------------------------
void Arvore::push(Node *no){
    NoPilha *novoNo = new NoPilha();
    if(novoNo == nullptr) return;
    novoNo->noDaArvore = no;
    novoNo->prox = topo;
    topo = novoNo;
}

// Função para gerar pilha de ascendentes recursivamente
bool Arvore::gerarPilAsc(Node *no, int val, NoPilha *pilha){
    if (no == nullptr) return false;
    if (no->info == val) return true;

    bool encontrado = false;
    if (no->info > val) encontrado = gerarPilAsc(no->esq, val, pilha);
    else encontrado = gerarPilAsc(no->dir, val, pilha);

    if(encontrado) push(no);
    return encontrado;
}

// Interface pública para gerar pilha de ascendentes
NoPilha* Arvore::gerarPilhaAsc(int val){
    NoPilha* pilhaAscendentes = nullptr;
    gerarPilAsc(raiz, val, pilhaAscendentes);
    return pilhaAscendentes;
}

// ------------------------------------------------------------
// Função main: teste da árvore binária
// ------------------------------------------------------------
int main(){
    Arvore ar;
    ar.inserirPublico(8);
    ar.inserirPublico(6);
    ar.inserirPublico(10);
    ar.inserirPublico(4);
    ar.inserirPublico(7);
    ar.inserirPublico(11);
    ar.inserirPublico(9);
    ar.inserirPublico(5);

    ar.alturaRecursivaPublica();
    ar.analiseSubArvoresFolhasPublico();
    ar.imprimirExtremidades(ar.raiz);
    ar.imprimirAscendentes(5);
}
