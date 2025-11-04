package codes.threads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Semaphore_2 {
    private static final Semaphore SEMAFORO = new Semaphore(3);

    private static final AtomicInteger QTD = new AtomicInteger(0);

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();

            boolean acquired = false;
            QTD.incrementAndGet();
            while (!acquired) {
                acquired = tryAcquire();
            }
            System.out.println(name + " has acquired a permit.");
            QTD.decrementAndGet();
            sleep();
            SEMAFORO.release();
        };

        Runnable r2 = () -> {
            System.out.println("Current waiting threads: " + QTD.get());
        };

        for(int i =0; i<100;i++)
        {
            executor.submit(r1);
        }

        executor.scheduleWithFixedDelay(r2, 0 , 100, TimeUnit.MILLISECONDS);
    }

    private static void sleep() {
        try {
            
            int tempoEspera = new Random().nextInt(6);
            tempoEspera++;
            Thread.sleep(tempoEspera * 1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private static boolean tryAcquire() {
        try {
            return SEMAFORO.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return false;
        }
    }
}
