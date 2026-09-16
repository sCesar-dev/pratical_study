# Objetivo: Checar se duas palavras possuem as mesmas letras nas mesmas quantidades.
# verificacao do comprimento das duas palavras
# verificacao de igualdade dos arrays
def verifica_anagrama(str_a, str_b):
    tam_str_a = len(str_a)
    tam_str_b = len(str_b)
    
    if (tam_str_a != tam_str_b):
        return False

    # if str_a == str_b:
    #     return True
    
    for i in range(0, tam_str_a):
        if str_a[i] != str_b[i]:
            return False
        return True

if __name__ == "__main__":
    string_a, string_b = input("Write two strings: ").split()
    print(verifica_anagrama(string_a,string_b))