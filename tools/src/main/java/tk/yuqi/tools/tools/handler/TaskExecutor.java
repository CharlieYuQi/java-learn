
package tk.yuqi.tools.tools.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.yuqi.tools.tools.ResultDTO;

/**
 * 类 TaskExecutor 的实现描述：TaskExecutor
 *
 * @since 2018/7/15
 */
@Component
@Slf4j
public class TaskExecutor {
    public ResultDTO execute(TaskContext taskContext) {
        String key = HandlerKey.key(taskContext.getGroup(), taskContext.getJobName(), taskContext.getBizKey());
        TaskHandler handler = HandlerRegister.getHandler(key);
        if (handler == null) {
            log.error("Can not find handler for key:{}", key);
            return ResultDTO.errorResult("No handler", "No handler");
        }
        return handler.execute(taskContext);
    }
}
