package codes.sockets;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer_1 {

private static final int PORT = 12345;

    private static final List<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("[Servidor] Ouvindo na porta  " + PORT + "...");
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("[Servidor] Conexão de: " + socket.getInetAddress().getHostAddress());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("[Servidor] Erro: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;
        private String nome;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                // 1) registrar cliente
                
                clients.add(out);
                out.println("Digite seu nome: ");
                nome = in.readLine();


                // 2) dar boas-vindas
                out.println("Bem-vindo "+ nome +"! Digite mensagens. Use 'exit' para sair.");
                broadcast(nome +"Chegou!", out);

                // 3) laço principal de leitura
                String line;
                while ((line = in.readLine()) != null) {
                    // TODO [Aluno]: se a linha for "exit", encerrar este cliente graciosamente (remover da lista e fechar socket).

                    // TODO [Aluno]: fazer broadcast da mensagem para TODOS os outros clientes.
                    //   - dica: itere sobre 'clients' e chame println(...)
                    //   - não envie de volta para o próprio 'out' (opcional)
                    if ("exit".equalsIgnoreCase(line.trim())) {
                        
                        out.println("Você saiu do chat. Até logo!");
                        break;
                    }
                    broadcast("[" + nome + "] " + line, out);
                }
            } catch (IOException e) {
                // queda de cliente é comum; logar e seguir
                System.out.println("Cliente desconectou: " + e.getMessage());
            } finally {
                // 4) garantir limpeza
                // TODO [Aluno]: remover o PrintWriter deste cliente de 'clients'
                if(out!= null)
                {
                    clients.remove(out);
                    broadcast("[Server] " + nome + "saiu do chat.", out);
                }
                try { socket.close(); } catch (IOException ignore) {}
            }
        }

        private void broadcast(String msg, PrintWriter sender) {
            // TODO [Aluno]: enviar 'msg' para todos em 'clients'.
            for (PrintWriter pw : clients) {
                if (pw != sender) {
                    pw.println(msg);
                }
            }
        }
    }
}
