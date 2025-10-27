# ------------------------------------------------------------
# Objetivo do código:
# Este código implementa duas operações principais sobre uma lista (estrutura de dados do tipo array dinâmico em Python):
#   1. Ordena os elementos da lista 'arr' em ordem crescente.
#   2. Realiza uma busca binária para verificar se o valor 'x' está presente.
#
# A estrutura de dados principal usada aqui é a **lista (list)**, que em Python é um vetor dinâmico.
# Isso significa que ela permite acesso direto por índice (O(1)), mas operações como inserção e remoção
# no meio da lista são custosas (O(n)).
#
# A busca binária é um algoritmo eficiente (O(log n)) que depende da lista estar **ordenada**.
# O código, portanto, primeiro ordena a lista e depois realiza a busca binária.
# ------------------------------------------------------------

def select_sort(arr, x):
    
    arr.sort()  # Ordena a lista 'arr' em ordem crescente (necessário para a busca binária)
    
    low = 0               # Limite inferior do intervalo de busca
    high = len(arr)       # Limite superior (inicialmente igual ao tamanho da lista)
    
    # Laço principal da busca binária: continua enquanto o intervalo for válido
    while (low <= high):
        middle = (low + high) // 2  # Calcula o índice do elemento central

        # Se o valor procurado for maior que o elemento do meio,
        # significa que ele está na metade direita da lista.
        if x > arr[middle]:
            low = middle + 1  # Atualiza o limite inferior
        
        # Se o valor for menor, ele está na metade esquerda.
        elif x < arr[middle]:
            high = middle - 1  # Atualiza o limite superior

        # Caso contrário, encontramos o valor.
        else:
            return middle  # Retorna o índice onde 'x' foi encontrado
    
    # Se o laço termina sem retorno, o valor não existe na lista.
    if low > high:
        print("Nao existe")  # Exibe mensagem informando ausência do valor


arr = [10, 5, 2, -2, 20]   
select_sort(arr, 0)         
