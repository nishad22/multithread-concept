package session1;

public class App {

    static void main() {
        SingletonExample singletonExample = SingletonExample.getInstance();
        SingletonExample singletonExample1 = SingletonExample.getInstance();

        if (singletonExample.equals(singletonExample1)) {
            System.out.println("Object is same");
        } else {
            System.out.println("Object is different");

        }
    }
}
