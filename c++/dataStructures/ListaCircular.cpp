#include "ListaCircular.hpp"

#ifdef _WIN32
#include <conio.h>
#else
#include <termios.h>
#include <unistd.h>

// Função getch() para Linux/Unix, lê um caractere sem pressionar Enter
char getch()
{
    struct termios oldt, newt;
    char ch;
    tcgetattr(STDIN_FILENO, &oldt);
    newt = oldt;
    newt.c_lflag &= ~(ICANON | ECHO);
    tcsetattr(STDIN_FILENO, TCSANOW, &newt);
    ch = getchar();
    tcsetattr(STDIN_FILENO, TCSANOW, &oldt);
    return ch;
}
#endif

// ------------------------------------------------------------
// Estrutura de dados:
// - Lista duplamente encadeada circular:
//     * Nodo2 -> armazena a palavra (info), ponteiros prox e ant
//     * ListaDupla mantém ponteiros:
//         inicio -> início da lista
//         atual  -> nó corrente
//         aux    -> ponteiro auxiliar para percorrer a lista
// - Lista circular: o próximo do último nó aponta para o primeiro e o anterior do primeiro aponta para o último
// ------------------------------------------------------------

void ListaDupla::imprimeLista()
{
    atual = inicio; // inicia a iteração a partir do início
    std::cout << "Palavra atual: " << std::endl;
    do
    {
        std::cout << atual->info << std::endl;
        atual = atual->prox;
    } while (atual != inicio); // percorre até voltar ao início

    std::cout << "A palavra atual é a inicial!" << std::endl;
}

void ListaDupla::editarPalavra(std::string palavra)
{
    atual->info = palavra; // altera a palavra do nó corrente
    imprimeLista();        // mostra lista atualizada
}

// ------------------------------------------------------------
// Inserção de uma nova palavra na lista circular
// - Caso lista vazia: novo nó aponta para ele mesmo (circularidade)
// - Caso lista não vazia: insere após o nó corrente, mantendo circularidade
// ------------------------------------------------------------
void ListaDupla::inserirPalavra(std::string palavra)
{
    Nodo2 *novo = new Nodo2();
    if (novo == nullptr) return;

    novo->info = palavra;

    if (inicio == nullptr)
    {
        novo->prox = novo; // aponta para si mesmo
        novo->ant = novo;
        inicio = novo;
        atual = novo;
        return;
    }
    else
    {
        novo->prox = inicio;
        novo->ant = atual;
        atual->prox = novo;
        inicio->ant = novo;
        atual = novo;
    }

    imprimeLista();
}

// ------------------------------------------------------------
// Eliminação de uma palavra da lista circular
// - Percorre a lista procurando a palavra
// - Ajusta ponteiros prox e ant para manter circularidade
// - Libera memória do nó removido
// ------------------------------------------------------------
void ListaDupla::eliminarPalavra(std::string palavra)
{
    aux = inicio;

    do
    {
        if (aux->info == palavra)
        {
            aux->ant->prox = aux->prox;
            aux->prox->ant = aux->ant;

            if (aux == inicio)
                inicio = aux->prox; // atualiza início se necessário
            if (aux == atual)
                atual = aux->prox;  // atualiza nó corrente

            delete aux;
            std::cout << "Palavra apagada" << std::endl;
            break;
        }
        aux = aux->prox;
    } while (aux != inicio);

    imprimeLista();
}

// Navega para o nó anterior (circular)
void ListaDupla::palavraAnterior()
{
    if (atual != nullptr)
    {
        atual = atual->ant;
        std::cout << "Retornou para a palavra: " << atual->info << std::endl;
    }
}

// Navega para o nó posterior (circular)
void ListaDupla::palavraPosterior()
{
    if (atual != nullptr)
    {
        atual = atual->prox;
        std::cout << "Retornou para a palavra: " << atual->info << std::endl;
    }
}

void ListaDupla::sair()
{
    // função placeholder
}

int main()
{
    ListaDupla editorTexto;
    char tecla;
    std::string palavra;

    while (true)
    {
        tecla = getch(); // captura tecla

        switch (tecla)
        {
        case 'E':
            std::cin >> palavra;
            editorTexto.editarPalavra(palavra);
            break;

        case 'D':
            std::cin >> palavra;
            editorTexto.inserirPalavra(palavra);
            break;

        case 'S':
            std::cin >> palavra;
            editorTexto.eliminarPalavra(palavra);
            break;

        case '<':
            editorTexto.palavraAnterior();
            break;

        case '>':
            editorTexto.palavraPosterior();
            break;

        case 'X':
            editorTexto.sair();
            return 0;
        }
    }
}
