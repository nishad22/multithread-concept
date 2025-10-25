package practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private Lock lock = new ReentrantLock(true); //will maintain FIFO order

    //it will act like inter-thread comm methods like wait(), notify() & notifyAll()
    private Condition condition = lock.newCondition();

    private boolean isReady = false;

    static void main() {

        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();

        Runnable r1 = () -> {
            reentrantLockExample.produce();
        };

        Runnable r2 = () -> {
            reentrantLockExample.consumer();
        };

        var thread = new Thread(r1);
        var thread1 = new Thread(r2);

        thread.start();
        thread1.start();
    }

    private void consumer() {

        try {
            Thread.sleep(1000); // Let producer start first
            lock.lock();
            System.out.println("consumer method: ");
            while (!isReady) {
                System.out.println("consumer is in race condition");
                condition.await(); //await for data
            }
            Thread.sleep(1000);
            System.out.println("signal() call from consumer: ");
            isReady = false;
            condition.signal();//wake up producer
            System.out.println("consumer completed operation: ");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Lock is released from consumer");
            lock.unlock();
        }
    }

    private void produce() {

        try {
            lock.lock();

            System.out.println("producer method: ");
            Thread.sleep(1000);
            isReady = true;
            System.out.println("await() call from producer");
            Thread.sleep(1000);
            condition.await(); // wait for consumer to finish
            System.out.println("signal() call from producer");
            Thread.sleep(1000);
            condition.signal(); //wake up consumer
            System.out.println("hello");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Lock is released from producer");
            lock.unlock();
        }
    }

}
