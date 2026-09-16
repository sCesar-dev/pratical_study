"""vogais = a, e i, o, u
1. tupla com as vogais
2. laço iterando para cada char da string checar se existe no array
3. acrescer num contador
4. retornar esse valor
"""

def conta_vogais(palavra):
    vogais = ('a', 'e', 'i', 'o', 'u')
    cont = 0
    
    # for char in palavra:
    #     if char in vogais:
    #         cont += 1
    
    # for char in palavra:
    #     for i in range(0, len(vogais)):
    #         if char == vogais[i]:
    #             cont += 1
    
    for i in range(0, len(palavra)):
        for j in range(0, len(vogais)):
            if palavra[i] == vogais[j]:
                cont += 1
                
    return cont
if __name__ == "__main__":
    palavra = input("Digite uma palavra: ")
    print(conta_vogais(palavra))