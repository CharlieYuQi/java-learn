package tk.yuqi.tools.tools.statusmachine;

public interface StatusContext {

    /**
     * 获取业务类型
     *
     * @return
     */
    String getBizType();

    /**
     * 获取状态
     *
     * @return
     */
    String getCurrentStatus();
}
