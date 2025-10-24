package practice;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private void increment() {
        atomicInteger.incrementAndGet();
    }

    private int getCount() {
        return atomicInteger.get();
    }

    static void main() throws InterruptedException {

        AtomicExample atomicExample = new AtomicExample();

        Runnable r1 = () -> {
            for (int i = 1; i <= 1000; i++) {
                atomicExample.increment();
            }
        };

        var thread1 = new Thread(r1);
        var thread2 = new Thread(r1);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("the count: " + atomicExample.getCount());

    }
}
