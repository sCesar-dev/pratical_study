import java.util.concurrent.CountDownLatch;


class SystemCheck implements Runnable{
    
    private String nomeSistema;
    private int delayMillis;
    private CountDownLatch latch;
    
     
    public SystemCheck(String nomeSistema, int delayMillis, CountDownLatch latch){
        this.nomeSistema = nomeSistema;
        this.delayMillis = delayMillis;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        try {
            System.out.println("[CARREGANDO] Módulo de " + nomeSistema + " iniciando...");

            Thread.sleep(delayMillis);

            System.out.println("[OK] Módulo de " + nomeSistema + " carregado com sucesso!");
        } catch (InterruptedException e) {
            System.out.println("[FALHA] Módulo de " + nomeSistema + " falhou ao carregar.");
        } finally
        {
            latch.countDown();
        }
    }
}