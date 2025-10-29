package practice;

public class BarrierDemo {

    static void main() {

        Barrier barrier = new Barrier(3);

        Runnable r1 = () -> {

            try {
                System.out.println(Thread.currentThread().getName()+" is waiting for barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName()+" has crossed the barrier");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r1);
        var t3 = new Thread(r1);

        t1.start();
        t2.start();
        t3.start();


    }

}
