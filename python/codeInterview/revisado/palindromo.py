#  Verificar se uma palavra ou frase é idêntica quando lida de frente para trás ou de trás para frente 
#  1. Pegar a string e separar em dois arrays, sendo um deles o inverso do primeiro
# 2. Slicing / Range (range)

# ada
# casa
def verifica_palindromo(word):
    if word is None : return None
    clean_word = word.lower().strip()

    
    # inversed_string = clean_word[::-1]
    inversed_string = ""
    for i in range(len(clean_word)-1, -1, -1):
        inversed_string += clean_word[i]
    
    #asac
    #casa
    
    # if clean_word == inversed_string:
    #     return True
    # else: 
    #     return False
    print(inversed_string)
    for i in range(0, len(clean_word)):
        if clean_word[i] != inversed_string[i]:
            return False
    return True

if __name__ == '__main__':

    string = input("Digite uma palavra: ")
    print(verifica_palindromo(string))