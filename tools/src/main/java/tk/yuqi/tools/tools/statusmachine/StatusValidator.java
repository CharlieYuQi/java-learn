package tk.yuqi.tools.tools.statusmachine;

public interface StatusValidator {

    /**
     * 新状态是否ok
     *
     * @param ctx
     * @param newStatus
     * @return
     */
    boolean isPass(StatusContext ctx, String newStatus);
}
