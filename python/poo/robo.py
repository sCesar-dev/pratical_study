#!/usr/bin/env python3

from bateria import Bateria

class Robo(Bateria):

    def __init__(self, nome, carga):
        self.nome = nome
        super().__init__(carga)
    
    def saudacao(self):
        self.gastar(5)
        if self.get_nivel() < 5:
            print(f"Bateria muito baixa para falar.")
        else:
            print(f"Olá! Eu sou o robo {self.nome}")

if __name__ == "__main__":
        
    bill = Robo("Bill", 10)
    bill.carregar(5)
    print(f"{bill.get_nivel()}")
    bill.saudacao()
    bill.saudacao()
    bill.saudacao()