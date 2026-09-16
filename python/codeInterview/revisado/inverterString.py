# criar funcao pedindo a string como parametro
# escrever laço de repeticao percorrendo toda a string de forma invertida
# adicionar os valores invertidos em um string

def inversao_string(palavra):
    palavra_invertida = ""
    # for i in palavra[::-1]:
    #     palavra_invertida += i
    for char in range(len(palavra)-1, -1, -1):
        palavra_invertida += palavra[char]
        
    return palavra_invertida

if __name__ == "__main__":
    string = input("Digite uma palavra: ")
    print(inversao_string(string))