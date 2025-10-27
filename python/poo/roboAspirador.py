#!/usr/bin/env python3

from robo import Robo

class RoboAspirador(Robo):

    def __init__(self, nome):
        super().__init__(nome)

    
    def limpar(self):
        print(f"{self.nome} diz: Limpado o chão... VRUUUM!")

bill_asp = RoboAspirador(nome="Bill-asp")
bill_asp.saudacao()
bill_asp.limpar()