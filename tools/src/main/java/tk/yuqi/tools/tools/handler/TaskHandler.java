package tk.yuqi.tools.tools.handler;

import tk.yuqi.tools.tools.ResultDTO;

/**
 * 类 TaskHandler 的实现描述：TaskHandler
 *
 * @since 2018/7/15
 */
public interface TaskHandler {

    ResultDTO execute(TaskContext taskContext);
}
