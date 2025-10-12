package virtualthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class StructuredTaskScopeExample {

    public static void main(String[] args) throws InterruptedException {

        //we do not pool virtual thread: we create new ones for every task,
        //and we dispose them after finished
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()){
            var process1 = new LongProcess(3, "result1");
            var process2 = new LongProcess(5,"result2");

            var process3 = new LongProcess(5,"result2",true);


            //we submit task in parallel
            StructuredTaskScope.Subtask<String>  res1 = scope.fork(process1);
            StructuredTaskScope.Subtask<String>  res2 = scope.fork(process2);
            StructuredTaskScope.Subtask<String>  res3 = scope.fork(process3);


            scope.join();

            //if there is any failure in any of the child threads: other threads will be terminated
            try {
                scope.throwIfFailed();
            } catch (ExecutionException e) {
                System.out.println("terminating all thread");
//                throw new RuntimeException(e);
            }

            //combine the result
            //here get() will not block the because join() waits for threads to finish
            System.out.println(res1.get() + " " +res2.get() +res3.get());

        }
    }
}
