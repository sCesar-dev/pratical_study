package codes.sockets;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;
public class ChatClient_1 {
    private static final String HOST = "10.166.255.234";
    private static final int PORT = 12345;
    byte[] name = new byte[1024];

    public static void main(String[] args) {
        System.out.println("Conectando em " + HOST + ":" + PORT + "...");
        try (
            Socket socket = new Socket(HOST, PORT);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            AtomicBoolean running = new AtomicBoolean(true);
            // Thread para ler mensagens do servidor
            Thread reader = new Thread(() -> {
                try {
                    String line;
                    while ((line = serverIn.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    // servidor pode fechar; encerrar leitor
                } finally {
                    running.set(false);
                }
            });
            reader.start();
            // Loop de envio (stdin -> servidor)
            String userLine;
            while (running.get() && (userLine = userIn.readLine()) != null) {
                // TODO [Aluno]: enviar a linha ao servidor
                serverOut.println(userLine);
                if ("exit".equalsIgnoreCase(userLine.trim())) {
                    break;
                }
            }
            // encerrar conexão
            try { reader.join(500); } catch (InterruptedException ignore) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
