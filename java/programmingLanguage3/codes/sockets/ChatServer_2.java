package codes.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer_2 {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server iniciado, aguardando coonexao...");
            Socket cliSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + cliSocket.getInetAddress().getHostAddress());

            try (
                InputStreamReader inReader = new InputStreamReader(cliSocket.getInputStream());
                BufferedReader reader = new BufferedReader(inReader);
                PrintWriter writer = new PrintWriter(cliSocket.getOutputStream(), true);

            ){
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Cliente: " + line);
                    writer.println("Eco: " + line);
                }
            } 
            
        } catch (Exception e) {
            System.out.println("Server error " + e.getMessage());
            e.printStackTrace();
        }
    }
}
