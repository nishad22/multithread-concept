package method_of_thread;

public class WaitAndNotifyExample {

    public void producer() throws InterruptedException {
        synchronized (this) {
            System.out.println("this is producer method");
            //once wait() is called it will release the lock immediately and other thread will acquire it
            wait();
            System.out.println("the producer method is complete");
        }
    }

    public void consumer() {
        synchronized (this) {
            System.out.println("this is consumer method ");
            //it will release the lock from object once it execute the remaining portion of code.
            notify();
            System.out.println("the consumer method is completed with notify()");
        }
    }

    public static void main(String[] args) {
        var obj = new WaitAndNotifyExample();

        var t1 = new Thread(() -> {
            try{
                obj.producer();
            }catch (InterruptedException exception){
                throw new RuntimeException();
            }
        });

        var t2 = new Thread(() -> {
            obj.consumer();
        });

        t1.start();
        t2.start();
    }
}
