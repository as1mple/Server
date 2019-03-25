import java.util.Random;

public class testing {
    public static void main(String[] args) {

        Runnable runnable =  new MyRunble();

        Thread thread = new Thread(runnable);

        thread.start();

        System.out.println(Thread.currentThread().getName());

        System.out.println(Thread.currentThread().getName());




    }
}
