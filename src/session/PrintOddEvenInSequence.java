package session;

public class PrintOddEvenInSequence {

    private int limit;
    private boolean isOddNum = true;

    public PrintOddEvenInSequence(int limit) {
        this.limit = limit;
    }

    void printOddNumber() throws InterruptedException {
        for (int i = 1; i <= limit; i += 2) {
            synchronized (this) {
                while (!isOddNum) {
                    wait();
                }
                isOddNum = false;
                System.out.println("this is odd number: " + i);
                notify();
            }
        }
    }

    void printEvenNumber() throws InterruptedException {
        for (int i = 2; i <= limit; i += 2) {
            synchronized (this) {
                while (isOddNum) {
                    wait();
                }
                isOddNum = true;
                System.out.println("this is even number: " + i);
                notify();
            }
        }
    }

    static void main() throws InterruptedException {

        PrintOddEvenInSequence printOddEvenInSequence = new PrintOddEvenInSequence(10);

        Runnable odd = () -> {
            try {
                printOddEvenInSequence.printOddNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable even = () -> {
            try {
                printOddEvenInSequence.printEvenNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var t1 = new Thread(odd);
        var t2 = new Thread(even);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
