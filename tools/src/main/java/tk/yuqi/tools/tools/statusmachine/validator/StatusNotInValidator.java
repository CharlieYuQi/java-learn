package tk.yuqi.tools.tools.statusmachine.validator;

import tk.yuqi.tools.tools.statusmachine.StatusContext;

public class StatusNotInValidator extends CommonStatusSetValidator {

    public StatusNotInValidator(String... statusArr) {

        super(statusArr);
    }

    @Override
    public boolean isPass(StatusContext ctx, String newStatus) {
        return !super.isPass(ctx, newStatus);
    }
}
