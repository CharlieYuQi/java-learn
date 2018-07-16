
package tk.yuqi.tools.tools.statusmachine.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.yuqi.tools.tools.statusmachine.StatusMachine;
import tk.yuqi.tools.tools.statusmachine.StatusMachineInit;
import tk.yuqi.tools.tools.statusmachine.StatusMachineMatcher;
import tk.yuqi.tools.tools.statusmachine.StatusUtils;
import tk.yuqi.tools.tools.statusmachine.validator.CommonStatusSetValidator;
import tk.yuqi.tools.tools.statusmachine.validator.StatusNotInValidator;

/**
 * 类 DemoStatusMachineImpl 的实现描述：DemoStatusMachineImpl
 *
 * @since 2018/7/16
 */
@Component
@Slf4j
public class DemoStatusMachineImpl implements StatusMachineInit {
    @Override
    public StatusMachineMatcher getMatcher() {
        return ctx -> "TARGET_TYPE".equals(ctx.getBizType());
    }

    @Override
    public StatusMachine init() {
        StatusMachine statusMachine = new StatusMachine();

        //NEW,SUBMIT 可以迁移到 NEW
        statusMachine.add("NEW",
                          new CommonStatusSetValidator("NEW", "SUBMIT"));

        //不是CANCEL 可以迁移到 SUBMIT
        statusMachine.add("SUBMIT", new StatusNotInValidator("CANCEL"));

        //原状态是SUBMIT，并且customsValidator校验通过 可以迁移到 CONFIRM
        statusMachine.add("CONFIRM",
                          StatusUtils
                              .and(new CommonStatusSetValidator("SUBMIT"), StatusUtils.bean("customsValidator")));

        //原状态是 SUBMIT，并且customsValidator校验通过 可以迁移到 CONFIRM
        statusMachine.add("CONFIRM",
                          StatusUtils
                              .and(new CommonStatusSetValidator("SUBMIT"), StatusUtils.bean("customsValidator")));

        //原状态是 CONFIRM，或者customsValidator校验通过 可以迁移到 RECEIPT
        statusMachine.add("RECEIPT",
                          StatusUtils
                              .or(new CommonStatusSetValidator("CONFIRM"), StatusUtils.bean("customsValidator")));

        return statusMachine;

    }
}
