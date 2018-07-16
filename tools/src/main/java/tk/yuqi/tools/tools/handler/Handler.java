package tk.yuqi.tools.tools.handler;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface Handler {

    String group() default "DEFAULT_GROUP";

    String jobName() default "DEFAULT_JOB_NAME";

    String bizKey() default "";
}
