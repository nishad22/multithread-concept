package executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    public static void main(String[] args) {
        List<Future<String>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i<9;i++) {
            Future<String> futureList = executorService.submit(new Processor(i));
            list.add(futureList);
        }

        list.stream().forEachOrdered(future -> {
            try {
                System.out.println(future.get());
            }  catch (RuntimeException | InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id: "+id;
    }
}