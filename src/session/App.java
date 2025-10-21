package session;

public class App {

    static void main() {
        SingletonExample singletonExample = SingletonExample.getInstance();
        SingletonExample singletonExample1 = SingletonExample.getInstance();

        if (singletonExample.equals(singletonExample1)){
            System.out.println("object are same");
        }else{
            System.out.println("object are different");
        }
    }
}
