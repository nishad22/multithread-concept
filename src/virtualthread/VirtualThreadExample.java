package virtualthread;

public class VirtualThreadExample implements Runnable {

    @Override
    public void run() {
        System.out.println("started..."+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished..."+Thread.currentThread().getName());
    }
}
