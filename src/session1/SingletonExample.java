package session1;

public class SingletonExample {

    private static SingletonExample singletonExample = null;

    private SingletonExample() {
    }

    public static SingletonExample getInstance() {
        if (singletonExample == null) {
            synchronized (SingletonExample.class) {
                if (singletonExample == null) {
                    singletonExample = new SingletonExample();
                }
            }
        }
        return singletonExample;
    }
}
