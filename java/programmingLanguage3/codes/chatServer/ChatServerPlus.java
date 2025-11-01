package codes.chatServer;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServerPlus {
        private static final int port = 12346;
    private static final List<PrintWriter> connectedClients = new CopyOnWriteArrayList<>();
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        System.out.println("ChatServer iniciado...");

        try (
            ServerSocket serverSocket = new ServerSocket(port);
            Scanner scKeyboard = new Scanner (System.in);
        ) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Alguem se conectou: " + clientSocket.getRemoteSocketAddress());

                ClientHandler handler = new ClientHandler(clientSocket, connectedClients);
                // new Thread(handler).start();
                pool.submit(handler);

            }
        } catch (Exception e) {
            System.out.println("Erro no chat: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
