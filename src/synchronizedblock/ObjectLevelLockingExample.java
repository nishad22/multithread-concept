package synchronizedblock;

public class ObjectLevelLockingExample {

    public synchronized void instanceMethod()  {
        System.out.println(Thread.currentThread().getName()+" entered instance method");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException();
        }

        System.out.println(Thread.currentThread().getName()+" finished instance method");
    }

    public static void main(String[] args) {

        var obj1 = new ObjectLevelLockingExample();
        var obj2 = new ObjectLevelLockingExample();

        Runnable r1 = obj1::instanceMethod;
        Runnable r2 = obj2::instanceMethod;

        new Thread(r1, "First thread").start();
        new Thread(r2, "Second thread").start();

    }
}
