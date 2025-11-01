package codes.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executors_SingleThread_Callable {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(new MeuRunnable());
            executor.execute(new MeuRunnable());
            executor.execute(new MeuRunnable());
            Future<?> future = executor.submit(new MeuRunnable());
            System.out.println(future.isDone());
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }

    }
}