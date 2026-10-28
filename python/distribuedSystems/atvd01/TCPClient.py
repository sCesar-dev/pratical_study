import socket
import time

# Nome das duplas: Samuel Cesar e Eduardo Veneza
# Função principal do cliente
def start_client():
    server_address = '127.0.0.1'
    server_port = 8000

    # Criação do socket TCP e conexão com o servidor
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((server_address, server_port))
        print("[INFO] Conectado ao servidor.")

        # Envia 10 mensagens para o servidor
        while True:
            message = input("Digite sua mensagem e ela retornará 3 vezes maior (digite SAIR para encerrar): ")
            if message.upper() == "SAIR":
                break
            client_socket.send(message.encode())
            data = client_socket.recv(40).decode()
            print(f"Recebido: {data}")

# Início da execução
if __name__ == "__main__":
    start_client()
