import Pyro5.api

@Pyro5.api.expose
class Trabalhador:    
    def calcular_produto_escalar(self, linha, coluna):
        if len(linha) != len(coluna):
            return -1
        
        produto_escalar = 0
        for i in range(len(linha)):
            produto_escalar += linha[i] * coluna[i]
        return produto_escalar

        
        
# if __name__ == "__main__":
#     matriz1 = ([1, 2, 3], [4, 5, 6], [7, 8, 9])
#     matriz2 = ([9,8,7], [6,5,4], [3,2,1])
    
#     matriz_resultante = [[" " for _ in range(3)] for _ in range(3)]
#     print(*matriz_resultante, sep='\n')
#     trabalhador = Trabalhador()
#     for i in range(3):
#         for j in range(3):
#             matriz_resultante[i][j] = trabalhador.calcular_produto_escalar(matriz1[i], matriz2[j])
    
#     print(*matriz_resultante, sep='\n')
