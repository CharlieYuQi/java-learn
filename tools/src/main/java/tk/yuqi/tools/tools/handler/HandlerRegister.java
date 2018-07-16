
package tk.yuqi.tools.tools.handler;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 类 HandlerRegister 的实现描述：HandlerRegister
 *
 * @since 2018/7/15
 */
@Component
@Slf4j
public class HandlerRegister implements BeanPostProcessor {

    private static Map<String, TaskHandler> handlerMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {

        if (!(bean instanceof TaskHandler)) {
            return bean;
        }

        Class<?> beanClass = bean.getClass();
        Handler handler = beanClass.getAnnotation(Handler.class);
        if (handler == null) {
            return bean;
        }

        String key = HandlerKey.key(handler.group(), handler.jobName(), handler.bizKey());
        if (handlerMap.containsKey(key)) {
            throw new BeanInstantiationException(this.getClass(), "Task Handler duplicated. key:" + key);
        }

        log.warn("Register Handler. key={}, class={}", key, beanClass.getName());
        handlerMap.put(key, (TaskHandler)bean);
        return bean;
    }

    public static TaskHandler getHandler(String key) {
        return handlerMap.get(key);
    }
}
