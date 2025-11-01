package codes.threads;

public class Thread_1 {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(t.getName());

        Thread t1 = new Thread(new MeuRunnable());
        t1.start();

        Thread t2 = new Thread(
            () -> System.out.println(Thread.currentThread().getName())
        );
        t2.start();
    }
}
