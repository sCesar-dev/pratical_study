# programming-language-I — Exemplos de sintaxe (PT-BR)

Esta pasta reúne pequenos exemplos e exercícios usados no curso "Programação I" para praticar conceitos básicos de C/C++.

Função do conteúdo:
- Mostrar uso de ponteiros, alocação dinâmica, manipulação de arrays, strings e funções.

Tópicos e arquivos de exemplo:
- `function-and-array/` — exemplos de funções que manipulam arrays (`inverseArray.cpp`, `cuttingNumber.cpp`).
- `pointers/` — vários exemplos sobre ponteiros, alocação dinâmica (`malloc_example.cpp`), ponteiro-para-ponteiro e manipulação de memória.
- `enum_sintax/` — exemplo de uso de enumerações (`month_day.cpp`).
- `dynamic_allocation/` — exemplo de uso de `malloc` em C++ (`malloc_example.cpp`).
- `strings/` e `structs/` — exemplos relacionados a strings e estruturas.

Como compilar:
```bash
g++ -std=c++17 arquivo.cpp -o arquivo && ./arquivo
```

Recomendações:
- Reescreva exemplos usando boas práticas (centralizar lógica em funções, evitar variáveis globais).
- Experimente ferramentas de análise estática e detectores de memory leak.
