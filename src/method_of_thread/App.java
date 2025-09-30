package method_of_thread;

public class App{
    public static void main(String[] args) {
        var producerConsumer = new ProducerConsumerPattern();

        var v1 = new Thread(new ProducerConsumerPattern.Producer(producerConsumer));
        var v2 =  new Thread(new ProducerConsumerPattern.Consumer(producerConsumer));

        v1.start();
        v2.start();

    }
}
