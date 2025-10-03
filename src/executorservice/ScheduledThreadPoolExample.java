package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new StockMarket(),1000,2000, TimeUnit.MILLISECONDS);


    }
}

class StockMarket implements Runnable{

    @Override
    public void run() {
        System.out.println("Stock market data updating and downloading");
    }
}
