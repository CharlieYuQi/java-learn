package tk.yuqibit.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by YuQi on 2017/5/12.
 */
@SpringBootApplication
public class BootApplication {
    private static Logger logger = LoggerFactory.getLogger(BootApplication.class);

    public static void main(String[] args) throws Exception {
        logger.info("Application Starting...");
        ApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
        ctx.getBean(tk.yuqibit.nio.basic.TimeServer.class).bind(8080);
    }
}
