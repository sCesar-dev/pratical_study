# Objetivo: Verificar se o número só é divisível por 1 e por ele mesmo.
# 

def numero_primo(value):
    
    cont = 0
    
    if value <= 1: return False
    
    if value == 2 : return True
    
    for i in range(1, value+1):
        
        if value % i == 0:
            cont += 1
    if cont == 2:
        return True
    else: 
        return False
        

if __name__ == '__main__':
    number = int(input("Digite um numero: "))
    numero_primo(number)