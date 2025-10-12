package virtualthread;

import java.util.concurrent.Callable;

public class FutureExample implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("The thread has started");
        Thread.sleep(2000);
        System.out.println("The thread is finished");
        return "this is result";
    }
}
