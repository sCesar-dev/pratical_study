import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RocketLauncher {
    
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(4);

        executor.submit(new SystemCheck("Comunicacao", 1000, latch));
        executor.submit(new SystemCheck("Navegacao", 3000, latch));
        executor.submit(new SystemCheck("Clima", 2000, latch));
        executor.submit(new SystemCheck("Propulsao", 4000, latch));

        executor.submit(new LaunchControl(latch));

        executor.shutdown();
    }
}
