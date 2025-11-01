package codes.threads;

public class MeuRunnable implements Runnable{
    public void run()
    {
        String nome = Thread.currentThread().getName();
        System.out.println(nome + ": LP-III");
    }
}