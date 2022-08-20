package tk.yuqi.tools.tools.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Component
public class PoolTest {
    @Autowired
    private PoolService poolService;


//    ExecutorService executorService = Executors.newFixedThreadPool(2);
    ThreadPoolExecutor executorService;
    int size = 2;
    int taskSize = 10;

    @PostConstruct
    public void init(){
        executorService = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512));
        executorService.allowCoreThreadTimeOut(true);
    }


    public void runTask(){

        for (int index = 0; index < taskSize; index++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("add task:"+index);
            int finalIndex = index;
            executorService.submit(()->{
                for (int i = 0; i < size; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    poolService.output(finalIndex);
                }

            });
        }
//        executorService.shutdown();

    }
}
