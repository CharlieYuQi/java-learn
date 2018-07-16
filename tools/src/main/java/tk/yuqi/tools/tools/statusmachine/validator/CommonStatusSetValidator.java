package tk.yuqi.tools.tools.statusmachine.validator;

import java.util.HashSet;
import java.util.Set;

import tk.yuqi.tools.tools.statusmachine.StatusContext;
import tk.yuqi.tools.tools.statusmachine.StatusValidator;

public class CommonStatusSetValidator implements StatusValidator {

    private Set<String> statusList = new HashSet<>();

    public CommonStatusSetValidator(String... statusList) {

        for (String status : statusList) {
            this.statusList.add(status);
        }
    }

    @Override
    public boolean isPass(StatusContext ctx, String newStatus) {

        return statusList.contains(ctx.getCurrentStatus());
    }
}
