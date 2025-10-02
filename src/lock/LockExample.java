package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private static int count = 0;

    //it means that the order will maintain as value is true
    private static Lock lock = new ReentrantLock(true);

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
        t2.join();

        System.out.println("the counter value: "+count);
    }

    private static synchronized void increment() {

        //best approach is to use in try-catch block to avoid exception
        try {
            lock.lock(); //it will lock the resource
            count++;
        } finally {
            lock.unlock(); //it will release lock once code is complete
        }
    }
}
