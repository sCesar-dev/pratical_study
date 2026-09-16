# var aux com o somatorio
# 

def somar_elementos(lista_valores):
    aux = 0
    for valor in lista_valores:
        aux += valor
    
    # for i in range(0, len(lista_valores)):
    #     aux += lista_valores[i]

    return aux
if __name__ == "__main__":
    valores = [1,4,2,54,3,2,545,54]
    print(somar_elementos(valores))