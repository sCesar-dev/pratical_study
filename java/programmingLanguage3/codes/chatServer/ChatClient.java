package codes.chatServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class ChatClient {
    
    private static final String serverAddress = "localhost";
    private static final int serverPort = 12346;

    public static void main(String[] args) {
        
        System.out.println("Bem vindo ao Chat!");
        try (
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scKeyboard = new Scanner(System.in);
        ) {
            System.out.println("Conectado ao servidor " + serverAddress + ":" + serverPort);
            System.out.print("Digite seu nickname: ");
            String nickUser = scKeyboard.nextLine();
            out.println(nickUser);

            System.out.println("Voce pode começar a digitar suas mensagens (digite 'sair' para encerrar):");
            Thread readerThread = new Thread(() -> {
                try 
                {
                    String msgServer;
                    while ((msgServer = in.readLine()) != null) 
                    {
                        System.out.println(msgServer);
                    }
                } catch (IOException e) {
                    System.out.println("Desconectado do servidor: " + e.getMessage());
                }
            });
            readerThread.start();
            while (true)
            {
                String msgUser = scKeyboard.nextLine();
                out.println(msgUser);
                if ("sair".equalsIgnoreCase(msgUser)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Erro no ChatClient: " + e.getMessage());
        }
    }
}
