package tk.yuqi.tools.tools.reactor;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.time.FastDateFormat;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FluxTest {
    public static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    public static void main(String[] args) throws InterruptedException {
        int threadSize = 10;
        List<String> list =Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        RateLimiter rateLimiter = RateLimiter.create(100);
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        Flux.fromStream(list.stream())
                .parallel(threadSize)
                .runOn(Schedulers.boundedElastic())
                .doOnNext(item->{
                    rateLimiter.acquire();
                    System.out.println(fastDateFormat.format(new Date())+"----"+item);
                }).doOnTerminate(countDownLatch::countDown)
                .subscribe();
        boolean await = countDownLatch.await(100, TimeUnit.SECONDS);
        if (!await){
            System.out.println("time out");
        }

    }
}
