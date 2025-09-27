package method_of_thread;

public class ThreadsInJVM {

    public static void main(String[] args) {

        for (Thread t :Thread.getAllStackTraces().keySet()) {
            System.out.println("Thread name: "+t.getName()+" , state: "+t.getState());
        }
    }
}
