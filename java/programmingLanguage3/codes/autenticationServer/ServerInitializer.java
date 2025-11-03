package codes.autenticationServer;

import java.util.concurrent.CountDownLatch;

public class ServerInitializer {
        private CountDownLatch latch = new CountDownLatch(4);
    
    public CountDownLatch getLatch() {
            return latch;
        }

    public void waitForInicialization()
    {
        try {
            this.latch.await();
        } catch (Exception e) {
            System.out.println("Erro");
        } finally
        {
            System.out.println("\n========================================");
            System.out.println("Servidor Principal Online: Pronto para aceitar conexões (Socket.bind())");
            System.out.println("\n========================================");   

            System.out.println("[SISTEMA] Processo de inicialização concluído.");
        }
    }

    public void startServer()
    {
        waitForInicialization();
    }

}
