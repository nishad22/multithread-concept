package method_of_thread;

public class JoinThreadExample {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable1 = () -> {
            for (int i=0;i<3;i++){
                System.out.println("thread1: "+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable2 = () -> {
            for (int i=0;i<3;i++){
                System.out.println("thread2: "+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        var v1 = new Thread(runnable1);
        var v2 = new Thread(runnable2);

        v1.start();
        v2.start();
        v1.join();
        v2.join();
        System.out.println("thread execution finished");

        //list all threads in JVM
    }
}
