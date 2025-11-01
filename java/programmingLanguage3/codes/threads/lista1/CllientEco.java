package codes.threads.lista1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class CllientEco {
    public static void main(String[] args) {
        
        String host = "127.0.0.1";
        int port = 12345;

        try 
        (
            Socket socket = new Socket(host, port);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Conectado ao servidor em " + host + ":" + port);
            System.out.println("Digite uma mensagem (ou 'sair' para encerrar):");

            String userInput;

            while ((userInput = console.readLine()) != null) {
                if (userInput.equalsIgnoreCase("exit")) 
                {
                    System.out.println("Encerrando conexao...");
                    break;
                }

                writer.println(userInput);
                String response = reader.readLine();
                System.out.println("Server response: " + response);
            }
            
        } catch (Exception e) {
            System.out.println("Erro do server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}