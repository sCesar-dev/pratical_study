# Objetivo: Retornar a lista apenas com itens únicos.
# declarar um array vazio que armazenar os valores
# iterar sobre array, verificando valores novos e ignorando valores ja adicionados

def remover_duplicatas(lista):
    array_unico = []
    # for valor in lista:
    #     if valor not in array_unico:
    #         array_unico.append(valor)
            
    for i in range(0, len(lista)):
        if array_unico is None:
            array_unico.append(lista[i])
            pass
        for j in range(0, len(array_unico)):
            if lista[i] != array_unico[j-1]:
                array_unico.append(lista[i])
                pass

    return array_unico
if __name__ == "__main__":
    array = [1, 2, 2, 3, 4, 4, 3, 5, 9, 9, 5, 0, 3]
    print(remover_duplicatas(array))
    