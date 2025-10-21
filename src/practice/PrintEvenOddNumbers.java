package practice;

public class PrintEvenOddNumbers {

    private final Integer limit;
    private boolean isOddNum = true;

    public PrintEvenOddNumbers(Integer limit) {
        this.limit = limit;
    }

    public void printOddNumbers() throws InterruptedException {
        for (int i = 1; i <= limit; i+=2) {
            synchronized (this) {
                while (!isOddNum) { // Wait while it's not odd's turn
                    wait();
                }
                System.out.println("This is odd number: " + i);
                isOddNum = false;
//                Thread.sleep(2000);
                notify();
            }
        }
    }

    public void printEvenNumbers() throws InterruptedException {

        for (int i = 2; i <= limit; i+=2) {
            synchronized (this) {
                while (isOddNum) { // Wait while it's not odd's turn
                    wait();
                }
//                Thread.sleep(2000);
                isOddNum = true;
                System.out.println("This is even number: " + i);
                notify();
            }

        }

    }

    static void main() throws InterruptedException {
        PrintEvenOddNumbers printEvenOddNumbers = new PrintEvenOddNumbers(11);

        Runnable odd = () -> {
            try {
                printEvenOddNumbers.printOddNumbers();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable even = () -> {
            try {
                printEvenOddNumbers.printEvenNumbers();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var t1 = new Thread(even);
        var t2 = new Thread(odd);

        t1.start();
        t2.start();

//        t1.join();
//        t2.join();
    }
}
