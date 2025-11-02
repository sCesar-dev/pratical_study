"""Função: Calcula o fatorial de n usando recursão.

Entrada: n (int) >= 0
Saída: inteiro com o fatorial de n
"""

def fat(n):
    if n==0 or n==1:
        return 1
    
    return n * fat(n-1)

print(fat(5))