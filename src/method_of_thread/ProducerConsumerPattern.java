package method_of_thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerPattern {

    List<Integer> buffer = new LinkedList<>();
    int capacity = 4;

    public synchronized void producer() throws InterruptedException {
        if (buffer.size() == capacity) {
            System.out.println("Buffer is full, producer waiting ");
            wait();
        }
        System.out.println("Adding items with producer");
        for (int i = 0; i < capacity; i++) {
            buffer.add(i);
            System.out.println("Added value: " + i);
        }
        //wake to notify the consumer
        notify();
    }

    public synchronized void consumer() throws InterruptedException {
        if (buffer.size() < capacity) {
            System.out.println("Buffer is not full yet and waiting for consumer: ");
            wait();
        }

        while (!buffer.isEmpty()) {
            int item = buffer.remove(0);
            System.out.println("Consumer removes: " + item);
            Thread.sleep(300);
        }
        notify();
    }

    static class Consumer implements Runnable {
        private ProducerConsumerPattern producerConsumerPattern;

        public Consumer(ProducerConsumerPattern producerConsumerPattern) {
            this.producerConsumerPattern = producerConsumerPattern;
        }


        @Override
        public void run() {
            while (true) {
                try {

                    this.producerConsumerPattern.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Producer implements Runnable {
        private ProducerConsumerPattern producerConsumerPattern;

        public Producer(ProducerConsumerPattern producerConsumerPattern) {
            this.producerConsumerPattern = producerConsumerPattern;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    this.producerConsumerPattern.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

