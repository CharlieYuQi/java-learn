package tk.yuqi.tools.tools.statusmachine;

import java.util.HashMap;
import java.util.Map;

public class StatusMachine {

    private Map<String, StatusValidator> statusValidatorMap = new HashMap<>();

    public StatusMachine add(String bizStatus, StatusValidator checker) {

        statusValidatorMap.put(bizStatus, checker);
        return this;
    }

    public boolean isPass(StatusContext ctx, String newStatus) {

        StatusValidator checker = statusValidatorMap.get(newStatus);
        if (checker == null) {
            return true;
        }

        return checker.isPass(ctx, newStatus);
    }

}
