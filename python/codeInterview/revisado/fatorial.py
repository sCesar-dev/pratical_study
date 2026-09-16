# Objetivo: Multiplicar um número por todos os seus antecessores até 1.
# utilizar o parametro N e iterar sobre cada fator recursivamente
# criar verificador do N ser igual a 1, caso sim retorna
# a cada retorno ele multiplica pelo valor presente 

def fatorial(num):
    # if num == 1:
    #     return 1
    
    # return num * fatorial(num - 1)
    valor = 1
    for i in range(1, num+1):
        valor *= i
        
    return valor

if __name__ == "__main__":
    valor = int(input("Digite o numero a ser fatorado: "))
    print(fatorial(valor))