package session1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    private BlockingQueue<Integer> blockingQueue;

    public BlockingQueueDemo(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void producer() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("Element Added in queue: " + i * 2);
            blockingQueue.put(i * 2);
//            Thread.sleep(1000);
        }
        System.out.println(blockingQueue);
    }

    public void consumer() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("I am in consumer method");
            int element = blockingQueue.take();
            Thread.sleep(1000);
            System.out.println("element removed from queue: " + element + "queue size: " + blockingQueue.size());

        }
    }

    public static void main() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo(blockingQueue);
        Runnable runnableP = () -> {
            try {
                blockingQueueDemo.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable runnableC = () -> {
            try {
                blockingQueueDemo.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var t1 = new Thread(runnableP);
        var t2 = new Thread(runnableC);

        t1.start();
        t2.start();

//        t1.join();
//        t2.join();

    }
}
