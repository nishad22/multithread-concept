package session;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {

    private BlockingQueue<Integer> queue;
    private static final int breaker = -1;

    public ArrayBlockingQueueExample(BlockingQueue<Integer> queue) {
        this.queue = queue;
//        this.capacity = capacity;
    }

    void producer() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            queue.put(i);
//            System.out.println("Produced: " + i + " | Queue size: " + queue.size());
        }
        Thread.sleep(100);
//        System.out.println(queue);
        // Add poison pill to signal consumer to stop
        queue.put(breaker);
    }

    void consumer() throws InterruptedException {
        System.out.println("remove element: "+queue.take());
        while (true) {
            Integer ele = queue.take();
            if (ele.equals(breaker)) {
                break;
            }
            System.out.println(ele);
            Thread.sleep(100);
        }
//        System.out.println("remove element: "+queue.take());

    }

    static void main() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        ArrayBlockingQueueExample arrayBlockingQueueExample = new ArrayBlockingQueueExample(blockingQueue);
//        CountDownLatch count = new CountDownLatch(2);

        Runnable r1 = () -> {
            try {
                arrayBlockingQueueExample.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
//                count.countDown();
            }
        };

        Runnable r2 = () -> {
            try {
                arrayBlockingQueueExample.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
//                count.countDown();
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        // Wait for both threads to complete
//        count.await();
    }
}
