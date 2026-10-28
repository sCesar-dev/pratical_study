from trabalhador import Trabalhador
import Pyro5.api
import multiprocessing
matriz1 = ([1, 2, 3], [4, 5, 6], [7, 8, 9])
matriz2 = ([9, 8, 7], [6, 5, 4], [3, 2, 1])

class Coordenador:
    def __init__(self):
        self.matriz_resultante = [[" " for _ in range(3)] for _ in range(3)]
  
    def reconstruir_matriz(self):
        trabalho = Trabalhador()
        for i in range(3):
                for j in range(3):
                    linha_a = matriz1[i]
                    coluna_j = []
                    for linha in matriz2:
                        coluna_j.append(linha[j])                    
                    # coluna_b = [linha[j] for linha in matriz2]
                    self.matriz_resultante[i][j] = trabalho.calcular_produto_escalar(linha_a, coluna_j)

    def executar_reconstrucao_sincrona(self):
        


@Pyro5.api.