#!/usr/bin/env python3

import time

class SpeakSkill:

    def __init__(self):
        print(f"Skill de fala inicializada.")
    
    def execute(self, texto, duracao_segundos):
        print(f"[FALANDO]: {texto}(aguardando {duracao_segundos} segundos)")
        time.sleep(duracao_segundos)

