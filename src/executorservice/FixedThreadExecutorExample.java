package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i<9;i++) {
            executorService.execute(new Work(i));
        }
        //need to shut down
        //shutdown() will prevent scheduledThreadPoolExecutor to execute further task
        executorService.shutdown();

        //it will actually shut down all task
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)){
                //scheduledThreadPoolExecutor.shutdown();
            }
        } catch (InterruptedException exception){
            executorService.shutdown();
        }
    }
}

class Work implements Runnable{

    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id "+id+" is in work - thread id: "+Thread.currentThread().getId());
        long duration = (long) Math.random()*5;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
