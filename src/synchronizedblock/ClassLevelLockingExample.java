package synchronizedblock;

public class ClassLevelLockingExample {

    public synchronized static void instanceMethod()  {
        System.out.println(Thread.currentThread().getName()+" entered instance method");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException();
        }

        System.out.println(Thread.currentThread().getName()+" finished instance method");
    }

    public static void main(String[] args) {

        Runnable r1 = ClassLevelLockingExample::instanceMethod;
        Runnable r2 = ClassLevelLockingExample::instanceMethod;

        new Thread(r1, "First thread").start();
        new Thread(r2, "Second thread").start();

    }
}
