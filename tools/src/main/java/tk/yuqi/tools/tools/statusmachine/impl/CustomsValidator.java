
package tk.yuqi.tools.tools.statusmachine.impl;

import org.springframework.stereotype.Component;
import tk.yuqi.tools.tools.statusmachine.StatusContext;
import tk.yuqi.tools.tools.statusmachine.StatusValidator;

/**
 * 类 CustomsValidator 的实现描述：CustomsValidator
 *
 * @since 2018/7/16
 */
@Component("customsValidator")
public class CustomsValidator implements StatusValidator {
    @Override
    public boolean isPass(StatusContext ctx, String newStatus) {
        return false;
    }
}
