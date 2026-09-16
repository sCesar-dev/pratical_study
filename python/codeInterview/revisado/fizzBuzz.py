# Imprimir números de 1 a N, trocando múltiplos de 3 por Fizz, 5 por Buzz, e ambos por FizzBuzz.
# criar laco de iteracao
# verificar se ele e multiplo 3
# verificar se ele e multiplo 5
# verificar se e multiplo de ambos
def fizzBuzz(N):
    arr = ""
    for i in range(0, N+1):
        if i % 3 == 0 and i % 5 == 0:
            arr += "FizzBuzz"
        elif i % 3 == 0:
            arr += "Fizz"
        elif i % 5 == 0:
            arr += "Buzz"
        else: 
            arr += str(i)
        arr += ", "
    return arr
if __name__ == "__main__":
    total_numeros = int(input("Digite o numero total de numeros: "))
    print(fizzBuzz(total_numeros))