package tk.yuqi.tools.tools.statusmachine;

public interface StatusMachineInit {

    /**
     * 状态机类型
     *
     * @return
     */
    StatusMachineMatcher getMatcher();

    /**
     * 状态机初始化
     */
    StatusMachine init();

}
