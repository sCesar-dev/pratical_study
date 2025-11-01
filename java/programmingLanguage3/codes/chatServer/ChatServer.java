package codes.chatServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import codes.chatServer.ClientHandler;

import java.io.PrintWriter;

public class ChatServer {

    private static final int port = 12346;
    private static final List<PrintWriter> connectedClients = new CopyOnWriteArrayList<>();
    public static void main(String[] args) {
        System.out.println("ChatServer iniciado...");

        try (
            ServerSocket serverSocket = new ServerSocket(port);
            Scanner scKeyboard = new Scanner (System.in);
        ) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Alguem se conectou: " + clientSocket.getRemoteSocketAddress());

                ClientHandler handler = new ClientHandler(clientSocket, connectedClients);
                new Thread(handler).start();

            }
        } catch (Exception e) {
            System.out.println("Erro no chat: " + e.getMessage());
            e.printStackTrace();
        }
    }
}