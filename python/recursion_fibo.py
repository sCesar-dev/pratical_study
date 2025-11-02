"""Função: Calcula o n-ésimo termo da sequência de Fibonacci por recursão.

Entrada: n (int) >= 0
Saída: inteiro com o termo n da sequência
"""

def fibo(n):
    if n == 0:
        return 0
    
    if n==1:
        return 1

    return fibo(n - 1) + fibo(n - 2)

print(fibo(5))