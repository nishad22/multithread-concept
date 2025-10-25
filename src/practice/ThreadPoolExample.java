package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {

    static void main() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            Thread.sleep(1000);
            executorService.execute(new PerformTask(i));
        }

        executorService.shutdown();
    }
}

class PerformTask implements Runnable {

    private int i;

    public PerformTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < i; j++) {
            System.out.println("Task with ID: " + j + " and thread name " + Thread.currentThread().getName());
        }
    }
}
