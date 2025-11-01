package codes.chatServer;

import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket socket;
    private List<PrintWriter> clientes;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, List<PrintWriter> clientes) {
        this.socket = socket;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        // Inicializa fluxos de entrada/saída e registra o cliente na lista
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientes.add(this.out); // adiciona writer compartilhado para broadcast

            // Primeiro dado esperado do cliente: nickname
            String nickname = in.readLine();
            System.out.println("Usuario conectado: " + nickname);

            String msgCliente;
            // Lê mensagens do cliente até desconexão ou comando "sair"
            while ((msgCliente = in.readLine()) != null) {
                if ("sair".equalsIgnoreCase(msgCliente)) {
                    // cliente solicitou sair
                    break;
                }

                System.out.println("Mensagem recebida de " + nickname + ": " + msgCliente);

                // Envia a mensagem para todos os demais clientes
                broadcast("[ " + nickname + " ]" + ": " + msgCliente, out);
            }
        } catch (IOException e) {
            // Exceção de I/O indica desconexão ou erro de rede
            System.out.println("Cliente desconectado: " + e.getMessage());
        } finally {
            // Remove o cliente da lista e fecha socket
            if (this.out != null) {
                clientes.remove(this.out);
            }

            try {
                socket.close();
                System.out.println("Cliente: " + socket.getRemoteSocketAddress() + " desconectado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message, PrintWriter writeOrigin)
    {
        for (PrintWriter clientWriter : clientes) {
            if(clientWriter != writeOrigin)
            {
                clientWriter.println(message);
            }
        }
    }

}
