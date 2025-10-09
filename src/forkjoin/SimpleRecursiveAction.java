package forkjoin;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulateWork;

    public SimpleRecursiveAction(int simulateWork) {
        this.simulateWork = simulateWork;
    }

    @Override
    protected void compute() {
        if (simulateWork>100){
            System.out.println("Parallel execution and split the tasks..."+ simulateWork);
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulateWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulateWork/2);
            invokeAll(action1,action2);
            /*
            * // add the task in thread pool
            * action1.fork();
            * action2.fork();
            * // wait for this task to be finished
            * action1.join();
            * action2.join();
            * */
        } else {
            System.out.println("this task is rather small can be executed sequentially...");
            System.out.println("the size of task: "+simulateWork);
        }
    }


    public static void main(String[] args) {
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SimpleRecursiveAction action = new SimpleRecursiveAction(400);
        action.invoke();
    }
}
