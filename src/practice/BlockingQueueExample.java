package practice;

import java.util.LinkedList;
import java.util.Queue;

//Implement a thread-safe blocking queue with methods enqueue and dequeue in Java.
public class BlockingQueueExample {

    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueueExample( int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enqueue(int item) throws InterruptedException {
        while (queue.size() == capacity){
            wait();
        }
        queue.add(item);
        System.out.println("elements in queue: "+queue);
        notify();
    }

    public synchronized void dequeue() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println("queue is waiting to have element");
            wait();
        }
        int i = queue.poll();
        notify();
        System.out.println("element is removed from queue: "+i);
    }
}

class App{
    static void main() throws InterruptedException {

        BlockingQueueExample blockingQueueExample = new BlockingQueueExample(5);

        Runnable r1 = () ->{
            for (int i = 1; i<6;i++){
                try {
                    blockingQueueExample.enqueue(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable r2 = () -> {
            try {
                blockingQueueExample.dequeue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.
                join();
        t2.join();
    }
}
