package codes.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Semaphore_1 {
    private static final Semaphore SEMAFORO = new Semaphore(3);

    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();

            acquire();
            System.out.println(name + " is waiting for a permit.");
            sleep();
            SEMAFORO.release();
        };

        for(int i =0; i<100;i++)
        {
            executor.submit(r1);
        }

        executor.shutdown();
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

    private static void acquire() {
        try {
            SEMAFORO.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
