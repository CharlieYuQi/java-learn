package tk.yuqi.tools.tools.statusmachine;

public interface StatusMachineMatcher {

    /**
     * 是否匹配
     *
     * @param ctx
     * @return
     */
    boolean isMatch(StatusContext ctx);

}
