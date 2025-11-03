package codes.autenticationServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AutServer {
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newCachedThreadPool();

        ServerInitializer sInitializer = new ServerInitializer();

        executor.submit(new ModuleLoader("Configuracao", 6000, sInitializer.getLatch()));
        executor.submit(new ModuleLoader("Cache", 9000, sInitializer.getLatch()));
        executor.submit(new ModuleLoader("Chaves de Criptografia", 12000, sInitializer.getLatch()));
        executor.submit(new ModuleLoader("Conexao de Log", 4000, sInitializer.getLatch()));

        executor.submit(() -> sInitializer.startServer());

        executor.shutdown();
    }
}
