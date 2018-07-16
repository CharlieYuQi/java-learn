package tk.yuqi.tools.tools.statusmachine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import tk.yuqi.tools.tools.statusmachine.logic.StatusAndChecker;
import tk.yuqi.tools.tools.statusmachine.logic.StatusOrChecker;

/**
 * Created by xw.xw on 2017/12/22.
 */
@Component
public class StatusUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static List<Pair<StatusMachineMatcher, StatusMachine>> statusMachineTypeList = new ArrayList<>();

    private static List<StatusMachineInit> statusMachineInitList = new ArrayList<>();

    private static boolean init = false;

    public static StatusValidator and(StatusValidator... statusCheckers) {

        return new StatusAndChecker(statusCheckers);
    }

    public static StatusValidator or(StatusValidator... statusCheckers) {

        return new StatusOrChecker(statusCheckers);
    }

    public static StatusValidator bean(String name) {
        return new BeanNameValidator(name);
    }

    public static boolean isStatusPass(StatusContext ctx, String newStatus) {

        for (Pair<StatusMachineMatcher, StatusMachine> pair : statusMachineTypeList) {

            if (!pair.getLeft().isMatch(ctx)) {
                continue;
            }

            // 如果不通过，直接返回false
            if (!pair.getRight().isPass(ctx, newStatus)) {
                return false;
            }
        }

        // 没有匹配状态机，直接过
        return true;
    }

    public static void add(StatusMachineInit machine) {
        statusMachineInitList.add(machine);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        StatusUtils.applicationContext = applicationContext;
    }

    public static void init() {
        if (init) {
            return;
        }
        init = true;
        for (StatusMachineInit entry : statusMachineInitList) {
            Pair<StatusMachineMatcher, StatusMachine> pair = new MutablePair<>(entry.getMatcher(), entry.init());
            statusMachineTypeList.add(pair);
        }
    }

    private static class BeanNameValidator implements StatusValidator {

        private StatusValidator validator;

        public BeanNameValidator(String beanName) {
            Object bean = applicationContext.getBean(beanName);

            if (!(bean instanceof StatusValidator)) {
                throw new IllegalArgumentException("Bean Type Error. BeanName:" + beanName);
            }
            validator = (StatusValidator)bean;
        }

        @Override
        public boolean isPass(StatusContext ctx, String newStatus) {
            return validator.isPass(ctx, newStatus);
        }
    }
}
