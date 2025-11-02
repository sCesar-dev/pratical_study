# Binary Tree — Exercícios e exemplos (PT-BR)

Esta pasta contém implementações e exercícios relacionados a árvores binárias (BST e variantes) em C++.

Função do conteúdo:
- Demonstrar operações em árvores: inserir, remover, percorrer (in-order, pre-order, post-order), calcular altura e somar folhas.
- Fornecer exemplos para análise de algoritmos recursivos.

Arquivos relevantes:
- `Arvore.cpp` — implementação principal da árvore (inserção, travessias, altura, remoção se implementada).
- `basic.cpp` — casos de uso ou exemplos simples de uso da árvore.
- `EstritamenteBinaria.cpp` — verificação/implementação relacionada a árvores estritamente binárias.
- `Substituir.cpp` — exercício específico (ver código para detalhes).
- `Outros/IgualandoAltura.cpp` — algoritmo para igualar alturas entre subárvores (exercício).
- `Outros/SomaFolhas.cpp` — soma dos valores das folhas da árvore.
- `Lista02/*` — exercícios numerados (questões da lista 02), cada arquivo contém uma solução.
- `Prova-antiga/` — exemplos de questões de prova antigas, usados para estudo.

Como executar exemplos:
```bash
g++ -std=c++17 Arvore.cpp -o arvore && ./arvore
```

Observações:
- Leia os comentários dentro de cada arquivo para entender entradas e saídas esperadas.
- Muitos algoritmos de árvore são recursivos — revise conceitos de pilha de chamadas e complexidade.
