package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonnaciRecusiveTask extends RecursiveTask<Integer> {

    private int num = 0;

    public FibonnaciRecusiveTask(int num) {
        this.num = num;
    }

    @Override
    protected Integer compute() {
        if (num <= 1)
            return num;

//        int curr = 0;
//        int prev1 = 0;
//        int prev2 = 0;
//        for (int i = 2;i<num;i++){
//            curr = prev1 + prev2;
//            prev1 = prev2;
//            prev2 = curr;
//        }
//        return curr;

        FibonnaciRecusiveTask f1 = new FibonnaciRecusiveTask(num-1);
        FibonnaciRecusiveTask f2 = new FibonnaciRecusiveTask(num-2);

        f1.fork();
        f2.fork();

        //return f1.join() +f2.join();

        //the above will replaced with and below is the optimized way
        //where actual thread will execute the one part
        //another thread will associate with f2.

        return  f1.compute() + f2.join();
    }

    public static void main(String[] args) {
        ForkJoinPool pool =new  ForkJoinPool();
        System.out.println(pool.invoke(new FibonnaciRecusiveTask(25)));
    }
}
