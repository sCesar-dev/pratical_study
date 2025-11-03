package codes.autenticationServer;

import java.util.concurrent.CountDownLatch;
public class ModuleLoader implements Runnable{
    
    private String nomeSistema;
    private int delayMillis;
    private CountDownLatch latch;

    public ModuleLoader(String nomeSistema, int delayMillis, CountDownLatch latch) {
        this.nomeSistema = nomeSistema;
        this.delayMillis = delayMillis;
        this.latch = latch;
    }
    
    @Override
    public void run()
    {
        try {
            System.out.println("[CARREGANDO] Módulo " + nomeSistema + " iniciando...");
            Thread.sleep(delayMillis);
            System.out.println("[OK] Módulo " + nomeSistema + " carregado com sucesso!");
        } catch (InterruptedException e) {
            System.out.println("Falha no Modulo " + nomeSistema);
        } finally
        {
            latch.countDown();
        }
    }


}
