package thread;

public class LambdaSampleExample {

    public static void main(String[] args) {

        Runnable r1 = () -> {
            for (int i=0;i<5;i++){
                System.out.println("thread1: "+i);
            }
        };

        Runnable r2 = () -> {
            for (int i=0;i<5;i++){
                System.out.println("thread1: "+i);
            }
        };

        var v1 = new Thread(r1);
        var v2 = new Thread(r2);

        v1.start();
        v2.start();
    }
}
