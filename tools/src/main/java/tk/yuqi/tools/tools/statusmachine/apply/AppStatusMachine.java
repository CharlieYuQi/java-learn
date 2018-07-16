
package tk.yuqi.tools.tools.statusmachine.apply;

import org.springframework.stereotype.Component;
import tk.yuqi.tools.tools.statusmachine.StatusContext;
import tk.yuqi.tools.tools.statusmachine.StatusUtils;

/**
 * 类 AppStatusMachine 的实现描述：AppStatusMachine
 *
 * @since 2018/7/16
 */
@Component
public class AppStatusMachine {

    public boolean isPass() {
        return StatusUtils.isStatusPass(new StatusContext() {
            @Override
            public String getBizType() {
                return "TARGET_TYPE";
            }

            @Override
            public String getCurrentStatus() {
                return "SUBMIT";
            }
        }, "CONFIRM");
    }
}
