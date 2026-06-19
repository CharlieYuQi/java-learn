package tk.yuqi.tools.tools.handler;

import org.apache.commons.lang3.StringUtils;

public final class HandlerKey {

    private HandlerKey() {
        // utility class
    }

    public static String key(String group, String jobName, String bizKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(group).append("#")
          .append(jobName);
        if (StringUtils.isNotBlank(bizKey)) {
            sb.append("#").append(bizKey);
        }
        return sb.toString();
    }
}
