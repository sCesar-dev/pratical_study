package codes.threads.lista1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerEco {

    private ExecutorService pool;

    public ServerEco()
    {
        this.pool = Executors.newFixedThreadPool(20);
    }

    public void start()
    {
        int port = 12345;
        // ExecutorService pool = Executors.newFixedThreadPool(50);
    
        try (ServerSocket serverSocket= new ServerSocket(port)) {
            System.out.println("[Server] Aguardando conexao na porta: " + port);
            // Socket clientSocket = serverSocket.accept();
            // System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
    
                MeuRunnable runnable = new MeuRunnable(clientSocket, this);
    
                pool.execute(runnable);
            }
            // try (
            //     InputStreamReader inReader = new InputStreamReader(clientSocket.getInputStream());
            //     BufferedReader reader = new BufferedReader(inReader);
            //     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
            // ) {
            //     String line;
            //     while ((line = reader.readLine()) != null) {
            //         System.out.println("Cliente: " + line);
            //         writer.println("Eco: " + line);
            //     }
            // }
        } catch (Exception e) {
            System.out.println("Server error " + e.getMessage());
            e.printStackTrace();        
        } 
    }
    
    public static void main(String[] args) {
        
    }
}
