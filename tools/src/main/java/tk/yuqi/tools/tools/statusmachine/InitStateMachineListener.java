package tk.yuqi.tools.tools.statusmachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitStateMachineListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(InitStateMachineListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        logger.error("application context started.");
        if (event.getApplicationContext().getParent() == null) {
            logger.error("application context started. init statemachine");
            StatusUtils.init();
        }
    }
}
