package codes.threads;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> System.out.println("Tarefa 1 executando..."));
        executor.submit(() -> System.out.println("Tarefa 2 executando..."));
        executor.submit(() -> System.out.println("Tarefa 3 executando..."));

        executor.shutdown();    
    }
}