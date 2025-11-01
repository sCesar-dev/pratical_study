package codes.noticeServer;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoticeServer {

    private static final int port = 12345;

    private static final List<PrintWriter> connectedClients = new CopyOnWriteArrayList<>();
    public static void main(String[] args) {
        // Enunciado / função do código:
        // Este é um servidor de avisos (notice server) simples que aceita conexões TCP de clientes
        // e retransmite mensagens recebidas para todos os clientes conectados (broadcast).
        // Pode ser usado como base para um sistema de notificações em rede para estudos.

        System.out.println("NoticeServer is running on port " + port);
        try (
            // ServerSocket que aceita conexões na porta definida
            ServerSocket serverSocket = new ServerSocket(port);
            // Scanner disponível caso queiramos ler comandos do console (não usado ativamente aqui)
            Scanner scKeyboard = new Scanner(System.in);
        )
        {
            // Loop principal: aceita conexões e cria um handler para cada cliente
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Cria e inicia um handler em thread separada para gerenciar o cliente
                ClientHandler handler = new ClientHandler(clientSocket, connectedClients);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            // Log básico de erro
            System.out.println("Error in NoticeServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}