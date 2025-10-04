package concurrent_collection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        var count = new CountDownLatch(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("current has stop");
        for (int i=0;i<5;i++){
            executorService.execute(new Worker(i,count));
        }
        //this method
        System.out.println("await method has called");
        //the current thread will not execute until countdown comes to zero
        count.await();

        System.out.println("current thread is over and its started working");
    }

}

class Worker implements Runnable{

    private int id;
    private CountDownLatch latch;

    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }

    private void doWork() {
        System.out.println("Thread with ID: "+this.id+" start working");
    }

}
