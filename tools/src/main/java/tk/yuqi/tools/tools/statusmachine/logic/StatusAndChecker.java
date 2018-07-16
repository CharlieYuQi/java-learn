package tk.yuqi.tools.tools.statusmachine.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;
import tk.yuqi.tools.tools.statusmachine.StatusContext;
import tk.yuqi.tools.tools.statusmachine.StatusValidator;

public class StatusAndChecker implements StatusValidator {

    private List<StatusValidator> subCheckers = new ArrayList<StatusValidator>();

    public StatusAndChecker(List<StatusValidator> subCheckers) {
        this.subCheckers = subCheckers;
    }

    public StatusAndChecker(StatusValidator... subCheckers) {

        if (ArrayUtils.isEmpty(subCheckers)) {
            return;
        }

        for (StatusValidator sc : subCheckers) {
            this.subCheckers.add(sc);
        }

    }

    @Override
    public boolean isPass(StatusContext ctx, String newStatus) {

        if (CollectionUtils.isEmpty(subCheckers)) {
            return false;
        }

        for (StatusValidator checker : subCheckers) {

            if (checker == null) {
                return false;
            }

            if (!checker.isPass(ctx, newStatus)) {
                return false;
            }
        }

        return true;
    }
}
