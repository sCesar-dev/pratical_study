import java.util.concurrent.CountDownLatch;

public class LaunchControl implements Runnable{
    private CountDownLatch latch;

    public LaunchControl(CountDownLatch latch)
    {
        this.latch = latch;
    }

    @Override
    public void run()
    {
        try {
            latch.await();

            System.out.println("\n========================================");
            System.out.println("LANÇAMENTO AUTORIZADO! (T-0)");
            System.out.println("========================================");

        } catch (InterruptedException e) {
            System.out.println("Erro no sistema de lançamento.");
        } finally {
            System.out.println("Sistema de lançamento finalizado.");
        }
    }
}
