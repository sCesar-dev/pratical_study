#!/usr/bin/env python3

class Bateria:

    def __init__(self, carga):
        if carga > 100:
            carga = 100
        if carga < 0:
            carga = 0
        self.carga = carga
    
    def gastar(self, quantidade):
        if not self.carga <= 0:
            self.carga -= quantidade
        if self.carga < 0:
            self.carga = 0

    def gastar_energia_critica(self, quantidade):
        if (self.carga >= quantidade):
            self.carga -= quantidade
        else:
            raise ValueError('Valor da carga insuficiente!')
    
    def carregar(self, quantidade):
        if not self.carga >= 100:
            self.carga += quantidade
        if self.carga > 100:
            self.carga = 100

    def get_nivel(self):     
        return self.carga
    
    
if __name__ == "__main__":

    print("--- Testando Bateria ---")
    bateria = Bateria(carga=50)
    print(f"Nível inicial: {bateria.get_nivel()}%") # Mostra: 50%

    bateria.gastar(60) # self.carga vira -10, e é imediatamente corrigido para 0.
    print(f"Nível após gastar 60: {bateria.get_nivel()}%") # Mostra: 0%

    bateria.gastar(20) # A verificação `self.carga == 0` impede o gasto.
    print(f"Nível após tentar gastar mais: {bateria.get_nivel()}%") # Mostra: 0%

    bateria.carregar(120) # self.carga vira 120, e é imediatamente corrigido para 100.
    print(f"Nível após carregar 120: {bateria.get_nivel()}%") # Mostra: 100%

    # Teste de inicialização com valor inválido
    bateria_invalida = Bateria(carga=500)
    print(f"Nível de bateria inicializada com 500: {bateria_invalida.get_nivel()}%") # Mostra: 100%

    bateria_teste = Bateria(carga=20)
    try:
        print("Tentando gastar 30 de energia crítica...")
        bateria_teste.gastar_energia_critica(30)
        print("Operação bem-sucedida!")
    except ValueError as e:
        print(f"Ocorreu um erro esperado: {e}")
        print(f"Nível final da bateria: {bateria_teste.get_nivel()}%")