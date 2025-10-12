package virtualthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class ShutdownOnSuccessExample {

    public static void main(String[] args) throws InterruptedException {

        //we do not pool virtual thread: we create new ones for every task,
        //and we dispose them after finished
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()){
            var process1 = new LongProcess(3, "result1");
            var process2 = new LongProcess(5,"result2");

            //we submit task in parallel
            StructuredTaskScope.Subtask<String>  res1 = scope.fork(process1);
            StructuredTaskScope.Subtask<String>  res2 = scope.fork(process2);


            scope.join();

            String result = null;
            try {
                result = scope.result();
                System.out.println(result);
            } catch (ExecutionException e) {
                System.out.println("There is no solution");
            }

            //combine the result
            //here get() will not block the because join() waits for threads to finish
//            System.out.println(res1.get() + " " +res2.get());

        }
    }
}
