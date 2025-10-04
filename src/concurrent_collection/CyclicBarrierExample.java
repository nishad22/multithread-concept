package concurrent_collection;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //cyclic barrier will have 5 number of the parties/thread
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All task has been finished...");
            }
        });

        for (int i=0;i<5;i++){
            executorService.execute(new BarrierWorker(i+1,cyclicBarrier));
        }

        executorService.shutdown();

    }
}

class BarrierWorker implements Runnable{

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public BarrierWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("thread with ID "+this.id+" start with work...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //when all thread call await() as many as times as N
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        System.out.println("After the await...");
    }
}