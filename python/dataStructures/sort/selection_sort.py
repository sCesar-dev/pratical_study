# ------------------------------------------------------------
# Estrutura de dados:
# - Lista (list) do Python: vetor dinâmico com acesso direto O(1).
# Algoritmo implementado:
# - Selection Sort (O(n^2)) para ordenar a lista arr.
#   Funciona encontrando o menor elemento da sublista e colocando-o na posição correta.
# ------------------------------------------------------------

def selection_sort(arr):
    for i in range(0, len(arr)):
        min_idx = i  # Índice do menor elemento na sublista atual
        # Percorre a sublista a direita de i
        for j in range(i+1, len(arr)):
            if arr[j] < arr[min_idx]:
                min_idx = j
        # Coloca o menor elemento encontrado na posição i
        arr[i], arr[min_idx] = arr[min_idx], arr[i]

arr2 = [10, 5, 2, -2, 20, 10, 0, 7]
selection_sort(arr2)
print(arr2)
