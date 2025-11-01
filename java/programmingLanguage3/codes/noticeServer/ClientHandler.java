package codes.noticeServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler  implements Runnable{
    private Socket socket;
    private List<PrintWriter> clientes;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, List<PrintWriter> clientes)
    {
        this.socket = socket;
        this.clientes = clientes;
    }

    @Override
    public void run() 
    {
        // Inicializa I/O e adiciona o writer à lista de clientes
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientes.add(this.out);
            String msgCliente;

            // Lê linhas enviadas pelo cliente até desconexão ou comando 'exit'
            while ((msgCliente = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(msgCliente)) {
                    // cliente solicitou sair
                    break;
                }

                // Log local do servidor
                System.out.println("Message received: " + msgCliente);

                // Reenvia a mensagem para todos os clientes conectados (broadcast)
                broadcast("NOTICE: " + msgCliente);
            }
        } catch (IOException e) {
            // Exceção indica que o cliente desconectou ou ocorreu erro de rede
            System.out.println("Client disconnected: " + e.getMessage());
        } finally {
            // Remove o cliente da lista para não enviar mais mensagens a ele
            if (this.out != null) {
                clientes.remove(this.out);
            }

            try {
                socket.close();
                System.out.println("Client: " + socket.getRemoteSocketAddress() + " disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcast(String string) {
        for (PrintWriter client : clientes)
        {
            client.println(string);
        }
    }
}
