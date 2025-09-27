package synchronizedblock;

public class SynchronizedBlock {

    private static int count = 0;
    private static int count2 = 0;



    //creating two object to avoid the intrinsic lock problem
    Object lock1 = new Object();
    Object lock2 = new Object();

    private void increment() {
        synchronized (lock1){
            count++;
        }
    }

    private void increment2() {
        synchronized (lock2){
            count2++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
       SynchronizedBlock synchronizedBlock = new SynchronizedBlock();

        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++)
                synchronizedBlock.increment();
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 1000; i++)
                synchronizedBlock.increment2();
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("the counter value1: "+count);
        System.out.println("the counter value2: "+count2);


    }
}
