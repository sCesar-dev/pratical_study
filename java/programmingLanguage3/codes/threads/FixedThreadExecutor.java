package codes.threads;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExecutor {
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
    
        for(int i = 1; i <= 10; i++)
        {
            int threadID = i;
            executor.submit(() -> {
                System.out.println("Ini task " + threadID + " na thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}