package practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//This implementation provides a reentrant lock that allows the same thread to acquire the lock multiple times.
public class ReentrantLockDemo {

    private Lock lock = new ReentrantLock(true);

    static void main() {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

        Runnable runnable = () -> {
            System.out.println("thread 1: ");
            reentrantLockDemo.printNum(5);
        };

//        Runnable runnable1 = () -> {
//            System.out.println("thread 2: ");
//            reentrantLockDemo.printNum(5);
//        };

        var t1 = new Thread(runnable);
        var t2 = new Thread(runnable);

        t1.start();
        t2.start();
    }

    private void printNum(int limit) {
        lock.lock();
        for (int i = 1; i <= limit; i++) {
            System.out.println(i);
        }
        lock.unlock();
    }
}
