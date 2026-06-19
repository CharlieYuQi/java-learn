
package tk.yuqi.tools.tools.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.yuqi.tools.tools.ResultDTO;
import tk.yuqi.tools.tools.exception.ErrorMessage;
import tk.yuqi.tools.tools.exception.ErrorMessageException;

/**
 * 类 TaskExecutor 的实现描述：TaskExecutor
 *
 * @since 2018/7/15
 */
@Component
@Slf4j
public class TaskExecutor {
    public ResultDTO<?> execute(TaskContext taskContext) {
        String key = HandlerKey.key(taskContext.getGroup(), taskContext.getJobName(), taskContext.getBizKey());
        TaskHandler handler = HandlerRegister.getHandler(key);
        if (handler == null) {
            log.error("Can not find handler for key:{}", key);
            ErrorMessage errorMessage = ErrorMessage.of("HANDLER_NOT_FOUND", key);
            return ResultDTO.errorResult(errorMessage.getReadableCode(), errorMessage.getMessage());
        }

        try {
            return handler.execute(taskContext);
        } catch (Exception e) {
            log.error("Handler execution error for key:{}", key, e);
            ErrorMessage errorMessage = ErrorMessage.of("HANDLER_EXECUTION_ERROR", key);
            throw new ErrorMessageException(errorMessage, e);
        }
    }
}
