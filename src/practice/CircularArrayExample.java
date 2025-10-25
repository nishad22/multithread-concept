package practice;

public class CircularArrayExample {

    private final Integer[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count;

    public CircularArrayExample(int capacity) {
        this.buffer = new Integer[capacity];
    }

    private synchronized void put(int i) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[tail] = i;
        tail = (tail + 1) % buffer.length;
        count++;
        Thread.sleep(1000);
        notify();
    }

    private synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int i = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        Thread.sleep(1000);
        notify();
        return i;
    }

    static void main() throws InterruptedException {

        CircularArrayExample circularArrayExample = new CircularArrayExample(5);

        Runnable r1 = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    circularArrayExample.put(i);
                    System.out.println("Element added in circular array: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("element taken from circular array: "+circularArrayExample.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
