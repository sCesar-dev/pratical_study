package codes.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient_2 {
    
    public static void main(String[] args) {
        String SERVER_ADDRESS = "127.0.0.1";
        int SERVER_PORT = 12345;
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

        ) {

            System.out.println("Conectando ao server.....");
            System.out.println("Digite seu nickname: ");
            String nickname = scanner.nextLine();
            out.println(nickname);

            //incompleto
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
