package thread;

public class SampleExample {

    public static void main(String[] args) {

        var v1 = new Thread(new RunnableExample());
        var v2 = new Thread(new RunnableExample2());
        v1.start();
        v2.start();
    }
}
