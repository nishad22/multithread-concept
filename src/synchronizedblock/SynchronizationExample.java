package synchronizedblock;

public class SynchronizationExample {


    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () -> {
            for (int i=0;i<1000;i++)
                increment();
        };

        Runnable r2 = () -> {
            for (int i=0;i<1000;i++)
                increment();
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();

        System.out.println("the counter value: "+count);

    }

    private static synchronized void increment() {
        count++;
    }
}
