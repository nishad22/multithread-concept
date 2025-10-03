package multithreadin_concept;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//below code will avoid cyclic dependencies
//solution:  we should make sure that each thread acquires the lock in the same order
public class SolutionOfDeadLockExample {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        SolutionOfDeadLockExample deadlock = new SolutionOfDeadLockExample();

        new Thread(deadlock::worker1, "worker1").start();
        new Thread(deadlock::worker2, "worker1").start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquires the lock1...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("Worker1 acquires the lock2...");

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock1.lock();
        System.out.println("Worker2 acquires the lock1...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("Worker2 acquires the lock2...");

        lock1.unlock();
        lock2.unlock();
    }
}
