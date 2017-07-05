package tk.yuqibit.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by YuQi on 2017/5/17.
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                c.countDown();
            }).start();
        }

        c.await();
        System.out.println("main thread");
    }
}
