package practice;

import java.util.LinkedList;
import java.util.Queue;

//todo
public class QueueProducerConsumer {

    private Queue<Integer> queue;
    private int capacity;

    public QueueProducerConsumer(Queue<Integer> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    static void main() throws InterruptedException {
        Queue<Integer> addQueue = new LinkedList<>();
        QueueProducerConsumer queueProducerConsumer = new QueueProducerConsumer(addQueue, 10);
        Runnable r1 = () -> {
            try {
                queueProducerConsumer.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable r2 = () -> {
            try {
                queueProducerConsumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    private synchronized void consumer() throws InterruptedException {

        while (queue.size() != capacity) {
            wait();
        }

        while (!queue.isEmpty()) {
            Thread.sleep(1000);
            System.out.println("element removed from queue " + queue.poll() + " of size " + queue.size());
        }
        notify();
    }

    private synchronized void producer() throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        for (int i = 1; i <= capacity; i++) {
            queue.add(i * 2);
            Thread.sleep(1000);
            System.out.println("Element added in queue: "+i*2+" of size "+queue.size());
        }
        notify();
    }


}
