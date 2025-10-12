package virtualthread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //approach#1 with builder
        var builder = Thread.ofVirtual().name("virtual thread",0);

        //as virtual thread are daemon thread we need to start and then call join method
        var t1 = builder.start(new VirtualThreadExample());
        var t2 = builder.start(new VirtualThreadExample());

        t1.join();
        t2.join();

        System.out.println("-----------------------------");

        //approach#2 with factory
        var factory = Thread.ofVirtual().name("virtual thread",0).factory();

        var t3 = factory.newThread(new VirtualThreadExample());
        var t4 = factory.newThread(new VirtualThreadExample());

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        //approach#3 without Runnable Interface
        System.out.println("-----------------------------");

        var factory1 = Thread.ofVirtual().name("virtual thread",0).factory();

        var t5 = factory1.newThread(new WithoutRunnableInterface()::run);
        var t6 = factory1.newThread(new WithoutRunnableInterface()::run);

        t5.start();
        t6.start();

        t5.join();
        t6.join();

        System.out.println("----------------------------");
        //approach#4 with ExecutorService
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.submit(new WithoutRunnableInterface()::run);
            executorService.submit(new WithoutRunnableInterface()::run);
            executorService.submit(new WithoutRunnableInterface()::run);
        }

        System.out.println("-------------Future Example---------------");

        ExecutorService future = Executors.newFixedThreadPool(5);
        Future<String> result = future.submit(new FutureExample());

        if (!result.isDone()){
            System.out.println("This is the main thread");
        }

        //it blocks the main thread
        String res = result.get();
        System.out.println(res);

        System.out.println("-------------Completable Future Example---------------");
        ExecutorService compFuture1= Executors.newFixedThreadPool(5);
        ExecutorService compFuture2 = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync(() -> "Hello World ",compFuture1)
                .thenApplyAsync(String::toLowerCase, compFuture2)
                .thenApply(s -> s + "something")
                .thenAccept(System.out::println);

        //how to combine result from multiple completable future
        CompletableFuture.supplyAsync(() -> "combine result from multiple completable future ")
                .thenCombine(CompletableFuture.supplyAsync(() -> " tutorial"),(s1,s2)-> s1 +"-"+s2)
                .thenAccept(System.out::println);
    }
}
