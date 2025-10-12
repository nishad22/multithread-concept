package virtualthread;

import java.time.Duration;
import java.util.concurrent.Callable;

public class LongProcess implements Callable<String> {

    private int timeToSleep;
    private String result;
    private boolean fail;

    public LongProcess(int timeToSleep, String result) {
        this.timeToSleep = timeToSleep;
        this.result = result;
    }

    public LongProcess(int timeToSleep, String result, boolean fail) {
        this.timeToSleep = timeToSleep;
        this.result = result;
        this.fail = fail;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Starting the thread...");
        Thread.sleep(Duration.ofSeconds(timeToSleep));
        if (fail){
            System.out.println("Thread has failed");
            throw new RuntimeException("Error");
        }
        System.out.println("Finishing the thread...");

        return result;
    }
}
