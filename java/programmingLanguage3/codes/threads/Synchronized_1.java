package codes.threads;

public class Synchronized_1 {
    // static int x = -1;
    private static int i = 0;
    public static void main(String[] args) {
        MeuRunnable runnable = new MeuRunnable();
        Thread t01 = new Thread(runnable);
        Thread t02 = new Thread(runnable);
        Thread t03 = new Thread(runnable);
        Thread t04 = new Thread(runnable);

        t01.start();
        t02.start();
        t03.start();
        t04.start();
    }

    public static class MeuRunnable implements Runnable 
    {
        // static Object Lock1 = new Object();
        // static Object Lock2 = new Object();
    
        @Override
        public void run()
        {
            // synchronized(this){
            //     x++;
            //     String name = Thread.currentThread().getName();
            //     System.out.println(name + ":" + x);
            // }
            // synchronized(Lock1){
            //     x++;
            //     String name = Thread.currentThread().getName();
            //     System.out.println(name + ":" + x);
            // }
            // synchronized(Lock2){
            //     x++;
            //     String name = Thread.currentThread().getName();
            //     System.out.println(name + ":" + x);
            // }

            int j;
            synchronized(this)
            {
                i++;
                j = i * 2;
            }

            double jElevadoA100 = Math.pow(j, 100);
            double sqrt = Math.sqrt(jElevadoA100);
            System.out.println(sqrt);
        }
    }
}