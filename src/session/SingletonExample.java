package session;

import java.util.Objects;

public class SingletonExample {

    private static volatile SingletonExample singletonExample;
    private  String id;

//    private SingletonExample(SingletonExample singletonExample) {
//        this.singletonExample = singletonExample;
//    }

    private SingletonExample() {
        // Simulate some initialization work
        this.id = "Singleton-" + Thread.currentThread().getName() + "-" + System.nanoTime();
        System.out.println("Creating singleton instance: " + this.id);
    }

    public static SingletonExample getInstance() {
        if (singletonExample == null) {
            synchronized (SingletonExample.class) {
                if (singletonExample == null){
                    singletonExample = new SingletonExample();
                }
            }
        }
        return  singletonExample;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        // Since it's a Singleton, if it's the same class and not the same object reference,
//        // it means the Singleton pattern was violated or an invalid comparison is being made.
//        // However, for a strict Singleton, this check will always return true if 'this' and 'obj'
//        // are both valid instances of the Singleton, as there should only be one.
//        return true; // Or simply 'return this == obj;' as the primary check covers it.
//    }
}
