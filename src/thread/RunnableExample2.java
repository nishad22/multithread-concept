package thread;

public class RunnableExample2 implements Runnable{
    public void run() {
        for (int i=0;i<5;i++){
            System.out.println("thread2: "+i);
        }
    }
}
