# Study — Syntax, DS and OOP (C / C++ / Python / Java)

Repository for study examples and exercises on syntax, data structures (DS) and object-oriented programming (OOP) in C, C++, Python and Java.

## Main structure
- [c/README.md](c/README.md) — C exercises  
- [c++/README.md](c++/README.md) — C++ exercises and data structures  
- [java/README.md](java/README.md) — Java examples  
- python/ — Python scripts and exercises

## How to compile/run (quick examples)
- C:
  - gcc example.c -o example && ./example
- C++:
  - g++ -std=c++17 example.cpp -o example && ./example
- Java:
  - javac Example.java && java Example
- Python:
  - python3 example.py

## Examples and reference files
- C:
  - Weighted average exercise: [c/sintax-exercicies/3-values.c](c/sintax-exercicies/3-values.c) — `main`
- C++ (programming and DS):
  - Binary search tree (BST): [c++/Binary Tree/Arvore.cpp](c++/Binary Tree/Arvore.cpp) — methods like `Arvore::inserir`, `Arvore::alturaRecursiva`
  - Circular list: [c++/dataStructures/ListaCircular.cpp](c++/dataStructures/ListaCircular.cpp) — `ListaDupla::imprimeLista`
  - Reverse array: [c++/programming-language-I/function-and-array/inverseArray.cpp](c++/programming-language-I/function-and-array/inverseArray.cpp) — `inverteArr`
- Python:
  - Bubble sort: [python/dataStructures/sort/bubble_sort.py](python/dataStructures/sort/bubble_sort.py) — `bubble_sort`
  - Resource lock example: [python/poo/lockResource.py](python/poo/lockResource.py) — `LockResource` class
- Java:
  - Module overview: [java/README.md](java/README.md)

## Study best practices
- Compile and run each example separately.  
- Read file comments to understand intent.  
- Turn snippets into small functions or unit tests when possible.

## Summary of important code
Quick list of the most relevant study files with links:

- C
  - c/sintax-exercicies/3-values.c — weighted average / I/O
  - c/algorithms/README.md — (if present) algorithms in C
- C++
  - c++/Binary Tree/Arvore.cpp — BST (insert, remove, height, traversals)
  - c++/dataStructures/ListaCircular.cpp — circular / doubly linked list
  - c++/programming-language-I/function-and-array/inverseArray.cpp — arrays and functions
- Python
  - python/dataStructures/sort/bubble_sort.py — sorting implementations
  - python/poo/lockResource.py — OOP example with resource locking
- Java
  - java/README.md — Java overview and examples
  - java/src/ — look for classes and tests

## Contributions / personal organization
- Add new exercises per language folder (e.g., c/, c++/, python/, java/).  
- Use descriptive file names and clear comments.