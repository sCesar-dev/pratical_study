# ------------------------------------------------------------
# Estrutura de dados:
# - Lista (list) do Python: vetor dinâmico, acesso direto O(1).
# Algoritmo implementado:
# - Insertion Sort (O(n^2) no pior caso) para ordenar a lista arr.
#   Funciona "inserindo" cada elemento na posição correta em relação aos anteriores.
# ------------------------------------------------------------

def insertion_sort(arr):
    if len(arr) == 1:
        return -1  # Lista com um elemento já está ordenada
    
    # Percorre cada elemento da lista (exceto o primeiro)
    for i in range(1, len(arr)):
        j = i
        # Move o elemento atual para a posição correta na sublista anterior
        while j > 0 and arr[j-1] > arr[j]:
            arr[j-1], arr[j] = arr[j], arr[j-1]  # Troca de posição
            j -= 1

arr2 = [10, 5, 2, -2, 20, 10, 0, 7]
insertion_sort(arr2)
print(arr2)
