package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    public static void main(String[] args) {

        Worker worker = new Worker();
        Runnable r1 = () -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable r2 = () -> {

            try {
                worker.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exception){
            System.out.println("Exception caught: "+exception.getMessage());
        }

    }
}

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException{
        lock.lock();
        System.out.println("Produced method: ");
        //wait() --> condition.await()
        condition.await();
        System.out.println("Again in producer method");
    }

    public void consumer() throws InterruptedException {
        //want to make sure we should start with producer()
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer method: ");
        Thread.sleep(3000);
        //notify() --> condition.signal()
        condition.signal();
        lock.unlock();
    }

}
