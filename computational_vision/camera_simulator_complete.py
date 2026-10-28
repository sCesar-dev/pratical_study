"""
Simulador de Câmera e Distorções de Lente - Guia Prático de Geometria de Visão Computacional
Este script acompanha a análise teórica e fornece implementações manuais de NumPy + OpenCV
dos principais conceitos de transformações geométricas, rotações 3D (Rodrigues/Quatérnios) 
e distorções de lente.

Desenvolvido como material complementar para fins didáticos.
"""

import numpy as np
import cv2
import os

# =====================================================================
# 1. FUNÇÕES AUXILIARES DE COORDENADAS E INTERSEÇÕES (ÁLGEBRA LINEAR)
# =====================================================================

def para_homogeneo(pontos):
    """
    Converte pontos cartesianos (não-homogêneos) para coordenadas homogêneas.
    Matematicamente: x_tilde = [x, y, 1]^T para R^2 ou [x, y, z, 1]^T para R^3.
    """
    pontos = np.atleast_2d(pontos)
    uns = np.ones((pontos.shape[0], 1))
    return np.hstack((pontos, uns))

def para_inhomogeneo(pontos_hom):
    """
    Converte coordenadas homogêneas para cartesianas por meio da divisão pela última coordenada (w).
    Matematicamente: x = x_tilde / w. Trata o ponto no infinito se w for zero.
    """
    pontos_hom = np.atleast_2d(pontos_hom)
    w = pontos_hom[:, -1:]
    # Evita divisão por zero para pontos no infinito
    w_safe = np.where(np.abs(w) < 1e-8, 1e-8 * np.sign(w) + 1e-8 * (w == 0), w)
    return pontos_hom[:, :-1] / w_safe

def intersecao_linhas_2d(l1, l2):
    """
    Calcula a interseção de duas linhas em 2D usando o produto vetorial (cross product).
    Matematicamente: x_tilde = l1 x l2.
    """
    l1 = np.array(l1)
    l2 = np.array(l2)
    p_hom = np.cross(l1, l2)
    if np.abs(p_hom[-1]) < 1e-8:
        return "Ponto no infinito (as linhas são paralelas)"
    return para_inhomogeneo(p_hom)[0]

# =====================================================================
# 2. REPRESENTAÇÕES DE ROTAÇÃO 3D (FÓRMULA DE RODRIGUES E QUATÉRNIOS)
# =====================================================================

def formula_rodrigues(omega):
    """
    Calcula a matriz de rotação 3D correspondente ao vetor de rotação (ângulo-eixo) omega = theta * n_hat.
    Matematicamente: R = I + sin(theta) * [n]_x + (1 - cos(theta)) * [n]_x^2
    """
    omega = np.array(omega, dtype=float)
    theta = np.linalg.norm(omega)
    if theta < 1e-8:
        return np.eye(3)
    
    n_hat = omega / theta
    # Matriz anti-simétrica do produto vetorial [n]_x
    n_x = np.array([
        [0.0, -n_hat[2], n_hat[1]],
        [n_hat[2], 0.0, -n_hat[0]],
        [-n_hat[1], n_hat[0], 0.0]
    ])
    
    I = np.eye(3)
    R = I + np.sin(theta) * n_x + (1.0 - np.cos(theta)) * np.dot(n_x, n_x)
    return R

def quaternion_para_matriz(q):
    """
    Converte um quatérnio unitário q = (qx, qy, qz, qw) para uma matriz de rotação 3D SO(3).
    Baseado na Equação (2.41) do livro (Szeliski).
    """
    q = np.array(q, dtype=float)
    norm = np.linalg.norm(q)
    x, y, z, w = q / norm # garante que é unitário
    
    R = np.array([
        [1.0 - 2.0*(y**2 + z**2),       2.0*(x*y - z*w),       2.0*(x*z + y*w)],
        [      2.0*(x*y + z*w), 1.0 - 2.0*(x**2 + z**2),       2.0*(y*z - x*w)],
        [      2.0*(x*z - y*w),       2.0*(y*z + x*w), 1.0 - 2.0*(x**2 + y**2)]
    ])
    return R

def slerp(q0, q1, alpha):
    """
    Interpolação Linear Esférica (Slerp) entre dois quatérnios q0 e q1 com peso alpha em [0, 1].
    Matematicamente baseado na Equação (2.45).
    """
    q0 = np.array(q0, dtype=float) / np.linalg.norm(q0)
    q1 = np.array(q1, dtype=float) / np.linalg.norm(q1)
    
    dot = np.dot(q0, q1)
    
    # Se o produto escalar for negativo, inverte um quatérnio para escolher o caminho mais curto
    if dot < 0.0:
        q1 = -q1
        dot = -dot
        
    dot = np.clip(dot, -1.0, 1.0)
    theta_0 = np.arccos(dot)
    
    if np.abs(theta_0) < 1e-8:
        return q0
        
    sin_theta_0 = np.sin(theta_0)
    sin_theta_alpha = np.sin(theta_0 * alpha)
    sin_theta_remaining = np.sin(theta_0 * (1.0 - alpha))
    
    q_alpha = (sin_theta_remaining / sin_theta_0) * q0 + (sin_theta_alpha / sin_theta_0) * q1
    return q_alpha / np.linalg.norm(q_alpha)

# =====================================================================
# 3. PROJEÇÃO PERSPECTIVA E SIMULAÇÃO DE CÂMERA COM DISTORÇÃO RADIAL
# =====================================================================

def projetar_pontos_com_distorcao(pontos_3d, R, t, K, k1=0.0, k2=0.0):
    """
    Projeta pontos 3D do mundo para 2D na imagem aplicando a matriz intrínseca K,
    extrínsecos (R, t) e o modelo de distorção radial de Brown-Conrady (k1, k2).
    
    Onde a matemática aparece no código:
    1. Transformação 3D: p_c = R * p_w + t
    2. Projeção perspectiva: xc = x_c/z_c, yc = y_c/z_c
    3. Distorção radial: r^2 = xc^2 + yc^2; scale = 1 + k1*r^2 + k2*r^4; (xc_dist, yc_dist) = scale * (xc, yc)
    4. Mapeamento para pixels: u = fx * xc_dist + cx; v = fy * yc_dist + cy
    """
    pontos_projetados = []
    
    for pt_w in pontos_3d:
        # Transformação rígida para coordenadas da câmera (Extrínsecos)
        pt_c = np.dot(R, pt_w) + t
        
        # Clipagem simples: pontos devem estar em frente à câmera (z_c > 0)
        if pt_c[2] <= 1e-3:
            pontos_projetados.append(None)
            continue
            
        # Divisão Perspectiva (Normalização de coordenadas de câmera)
        xc = pt_c[0] / pt_c[2]
        yc = pt_c[1] / pt_c[2]
        
        # Modelo de Distorção de Lente Radial (Brown-Conrady)
        r2 = xc**2 + yc**2
        fator_radial = 1.0 + k1 * r2 + k2 * (r2**2)
        xc_dist = xc * fator_radial
        yc_dist = yc * fator_radial
        
        # Mapeamento Pixelar (Intrínsecos K)
        fx, fy = K[0, 0], K[1, 1]
        cx, cy = K[0, 2], K[1, 2]
        
        xs = fx * xc_dist + cx
        ys = fy * yc_dist + cy
        
        pontos_projetados.append((int(round(xs)), int(round(ys))))
        
    return pontos_projetados

def renderizar_cubo(vertices_3d, edges, R, t, K, k1=0.0, k2=0.0, width=500, height=500, num_samples=40):
    """
    Renderiza um cubo 3D amostrando pontos ao longo de suas arestas para visualizar
    as distorções de lente como curvas no espaço 2D da imagem.
    """
    img = np.ones((height, width, 3), dtype=np.uint8) * 255
    
    # Amostra pontos ao longo de cada aresta 3D para renderizar distorções curvilíneas reais
    for edge in edges:
        p1 = vertices_3d[edge[0]]
        p2 = vertices_3d[edge[1]]
        
        # Amostragem paramétrica (Eq. 2.9): r = (1 - lambda)*p + lambda*q
        edge_pts_3d = []
        for lamb in np.linspace(0.0, 1.0, num_samples):
            pt_w = (1.0 - lamb) * p1 + lamb * p2
            edge_pts_3d.append(pt_w)
            
        # Projeta a aresta amostrada
        pixels_projetados = projetar_pontos_com_distorcao(edge_pts_3d, R, t, K, k1, k2)
        
        # Desenha segmentos de reta sequenciais ligando as amostras
        for i in range(len(pixels_projetados) - 1):
            pt1 = pixels_projetados[i]
            pt2 = pixels_projetados[i+1]
            if pt1 is not None and pt2 is not None:
                # Clipagem na borda da imagem para visualização limpa
                if (0 <= pt1[0] < width and 0 <= pt1[1] < height) or (0 <= pt2[0] < width and 0 <= pt2[1] < height):
                    cv2.line(img, pt1, pt2, (0, 0, 150), 2)
                    
    # Projeta e renderiza os vértices principais como círculos Azuis
    pixels_vertices = projetar_pontos_com_distorcao(vertices_3d, R, t, K, k1, k2)
    for px in pixels_vertices:
        if px is not None:
            if 0 <= px[0] < width and 0 <= px[1] < height:
                cv2.circle(img, px, 6, (200, 0, 0), -1)
                
    return img

# =====================================================================
# 4. EXECUÇÃO DO PROJETO INTEGRADO E SALVAMENTO DE EXPERIMENTOS
# =====================================================================

if __name__ == "__main__":
    print("[INFO] Iniciando o Simulador Geométrico de Câmeras...")
    
    # 1. Definição do Cubo 3D no espaço do mundo (coordenadas cartesianas em metros/unidades)
    cube_vertices = np.array([
        [-1.0, -1.0, -1.0],
        [ 1.0, -1.0, -1.0],
        [ 1.0,  1.0, -1.0],
        [-1.0,  1.0, -1.0],
        [-1.0, -1.0,  1.0],
        [ 1.0, -1.0,  1.0],
        [ 1.0,  1.0,  1.0],
        [-1.0,  1.0,  1.0]
    ], dtype=float)

    cube_edges = [
        (0, 1), (1, 2), (2, 3), (3, 0), # Base inferior
        (4, 5), (5, 6), (6, 7), (7, 4), # Topo superior
        (0, 4), (1, 5), (2, 6), (3, 7)  # Arestas verticais de conexão
    ]

    # 2. Definição dos parâmetros intrínsecos de Câmera (K)
    # Câmera fictícia de 500x500 pixels com focal fx=fy=300 pixels e centro cx=cy=250.
    K_camera = np.array([
        [300.0,   0.0, 250.0],
        [  0.0, 300.0, 250.0],
        [  0.0,   0.0,   1.0]
    ], dtype=float)

    # 3. Definição dos parâmetros extrínsecos (Rotação + Translação)
    # Rotacionar o cubo 20° em X e 30° em Y usando rotações conhecidas
    theta_x = np.radians(20)
    theta_y = np.radians(30)
    Rx = np.array([
        [1.0, 0.0, 0.0],
        [0.0, np.cos(theta_x), -np.sin(theta_x)],
        [0.0, np.sin(theta_x), np.cos(theta_x)]
    ])
    Ry = np.array([
        [np.cos(theta_y), 0.0, np.sin(theta_y)],
        [0.0, 1.0, 0.0],
        [-np.sin(theta_y), 0.0, np.cos(theta_y)]
    ])
    R_extrinsico = np.dot(Rx, Ry)
    
    # Translação: posiciona o cubo a 3.5 unidades à frente da câmera (ao longo do eixo Z)
    t_extrinsico = np.array([0.2, -0.1, 3.5])

    # 4. Renderização das 3 condições de simulação de lente
    print("[PROCESSO] Renderizando Projeção Perspectiva Linear (Ideal)...")
    linear_img = renderizar_cubo(cube_vertices, cube_edges, R_extrinsico, t_extrinsico, K_camera, k1=0.0)
    
    print("[PROCESSO] Renderizando com Distorção Radial tipo Barril (k1 = 0.5)...")
    barrel_img = renderizar_cubo(cube_vertices, cube_edges, R_extrinsico, t_extrinsico, K_camera, k1=0.5)
    
    print("[PROCESSO] Renderizando com Distorção Radial tipo Almofada/Pincushion (k1 = -0.25)...")
    pincushion_img = renderizar_cubo(cube_vertices, cube_edges, R_extrinsico, t_extrinsico, K_camera, k1=-0.25)
    
    # 5. Salvando os arquivos finais
    cv2.imwrite("/workspace/scratch/cube_curved/linear.png", linear_img)
    cv2.imwrite("/workspace/scratch/cube_curved/barrel.png", barrel_img)
    cv2.imwrite("/workspace/scratch/cube_curved/pincushion.png", pincushion_img)
    
    print("[INFO] Todos os experimentos foram salvos com sucesso na pasta `/workspace/scratch/cube_curved/`.")
