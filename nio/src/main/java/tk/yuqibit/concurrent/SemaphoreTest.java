package tk.yuqibit.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by YuQi on 2017/5/17.
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    s.acquire();
                    System.out.println("acquire");
                    Thread.sleep(2000);
                    System.out.println("release");
                    s.release();
                } catch (InterruptedException e) {
                }
            });
        }

        threadPool.shutdown();
        System.err.println("exit");
    }
}
