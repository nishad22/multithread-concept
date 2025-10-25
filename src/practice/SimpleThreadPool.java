package practice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool {

    private final int poolSize;
    private final PoolWorker[] workers;
    private final BlockingQueue<Runnable> taskQueue;

    public SimpleThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.workers = new PoolWorker[poolSize];
        this.taskQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < 3; i++) {
            //System.out.println("Poolowrker is started");
            workers[i] = new PoolWorker();
            workers[i].start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
//        System.out.println("Task added in queue");
        taskQueue.put(task);
    }


    class PoolWorker extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("Taking out task from queue");
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static void main() throws InterruptedException {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(3);
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName()+" is executing task: ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " has finished task.");
        };

        for (int i = 0; i < 3; i++) {
            simpleThreadPool.submit(runnable);
        }

    }
}
