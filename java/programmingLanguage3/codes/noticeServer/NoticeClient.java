package codes.noticeServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NoticeClient {
    private static final int port = 12345;
    private static final String serverAddress = "127.0.0.1";

    public static void main(String[] args) {
        // Enunciado / função do código:
        // Cliente simples para o NoticeServer. Conecta ao servidor na porta definida e
        // permite digitar mensagens no console. Qualquer mensagem digitada é enviada ao
        // servidor, que por sua vez retransmite para todos os clientes conectados.

        System.out.println("NoticeClient connecting to " + serverAddress + ":" + port);
        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scKeyboard = new Scanner(System.in);
        ) {
            System.out.println("Connected to the server at " + serverAddress + ":" + port);
            System.out.println("Type your messages below (type 'exit' to quit):");

            // Thread que fica lendo o que o servidor envia e printando no console
            Thread readerThread = new Thread(() -> {
                try {
                    String msgServer;
                    while ((msgServer = in.readLine()) != null) {
                        System.out.println(msgServer);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server: " + e.getMessage());
                }
            });
            readerThread.start();

            // Loop principal de leitura do teclado e envio ao servidor
            while (true) {
                String msgUser = scKeyboard.nextLine();
                out.println(msgUser);
                if ("exit".equalsIgnoreCase(msgUser)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error in NoticeClient: " + e.getMessage());
        }
    }
}
