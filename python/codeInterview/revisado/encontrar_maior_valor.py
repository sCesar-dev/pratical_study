"""
    1. crio var aux que vai guardar o maior valor
    2. itero sobre os valores da lista
    3. no final retorno aux com o maior valor
"""

def encontrar_maior_valor(lista_num):
    aux = float('-inf')
    
    # for num in valor:
    #     if num > aux:
    #         aux = num
    
    for i in range(0, len(lista_num)):
        if lista_num[i] > aux:
            aux = lista_num[i]
    
    return aux
if __name__ == "__main__":
    lista = [1,213,1,23,3,4,545,-1,9,10]
    print(encontrar_maior_valor(lista))