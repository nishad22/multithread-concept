package forkjoin;

import java.util.concurrent.RecursiveTask;

public class RecursiveTaskExample extends RecursiveTask<Integer> {

    private int simulateWork;

    public RecursiveTaskExample(int simulateWork) {
        this.simulateWork = simulateWork;
    }

    @Override
    protected Integer compute() {
        if (simulateWork>100){
            System.out.println("Parallel execution and split the tasks..."+ simulateWork);
            RecursiveTaskExample action1 = new RecursiveTaskExample(simulateWork/2);
            RecursiveTaskExample action2 = new RecursiveTaskExample(simulateWork/2);
            //add task to thread pool
            action1.fork();
            action2.fork();

            // wait for the task to be finished
            int subSolution = 0;
            subSolution += action1.join();
            subSolution += action2.join();
            return subSolution;

        } else {
            return 2*simulateWork;
        }
    }


    public static void main(String[] args) {
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveTaskExample action = new RecursiveTaskExample(400);
        System.out.println(action.invoke());
    }
}
