# ------------------------------------------------------------
# Estrutura de dados:
# - Lista (list) do Python: vetor dinâmico com acesso direto O(1).
# Algoritmo implementado:
# - Ordenação da lista (usando sorted, O(n log n))
# - Busca binária recursiva (O(log n)) para encontrar o valor x.
# ------------------------------------------------------------

def recursion_select_sort(arr, x, low, high):
    # Caso base: intervalo inválido significa que o valor não está presente
    if low > high:
        return -1
    
    middle = (low + high) // 2  # Índice central
    
    if x == arr[middle]:
        return middle  # Valor encontrado
    
    # Valor maior que o central -> busca na metade direita
    if x > arr[middle]:
        return recursion_select_sort(arr, x, middle+1, high)
    
    # Valor menor -> busca na metade esquerda
    else:
        return recursion_select_sort(arr, x, low, middle-1)


arr = [10, 5, 2, -2, 20]
result = recursion_select_sort(sorted(arr), 0, 0, len(arr)-1)  # sorted retorna lista ordenada
if result == -1:
    print("Nao existe")
else:
    print(f"Posicao {result} do array")
