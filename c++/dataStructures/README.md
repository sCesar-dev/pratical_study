# dataStructures — Implementações (PT-BR)

Esta pasta contém implementações clássicas de estruturas de dados em C++: listas ligadas, listas circulares, pilhas e filas.

Função do conteúdo:
- Fornecer implementações manuais (sem uso da STL) para entender os detalhes internos de cada estrutura.

Arquivos principais:
- `ListaCircular.cpp`, `ListaCircular.hpp` — implementação de lista circular/duplamente ligada.
- `FilaEncadeada.cpp`, `FilaEncadeada.hpp` — fila encadeada (enqueue/dequeue).
- `Stack.cpp`, `Stack.hpp` — implementação de pilha (push/pop/top).
- `FilaEncadeada1.cpp`, `FilaEncadeada2.cpp` — variações e testes da fila encadeada.

Como compilar/executar um exemplo:
```bash
g++ -std=c++17 ListaCircular.cpp -o lista && ./lista
```