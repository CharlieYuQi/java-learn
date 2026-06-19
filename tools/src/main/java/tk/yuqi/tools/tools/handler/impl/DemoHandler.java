package tk.yuqi.tools.tools.handler.impl;

import tk.yuqi.tools.tools.ResultDTO;
import tk.yuqi.tools.tools.handler.Handler;
import tk.yuqi.tools.tools.handler.TaskContext;
import tk.yuqi.tools.tools.handler.TaskHandler;

/**
 * 类 DemoHandler 的实现描述：DemoHandler
 *
 * @since 2018/7/15
 */
@Handler(group = "DEFAULT_GROUP", jobName = "DEFAULT_JOB_NAME")
public class DemoHandler implements TaskHandler {
    @Override
    public ResultDTO<?> execute(TaskContext taskContext) {
        return ResultDTO.trueResult(Boolean.TRUE);
    }
}
