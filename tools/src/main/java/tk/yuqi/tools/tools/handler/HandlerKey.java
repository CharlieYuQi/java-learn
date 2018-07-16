package tk.yuqi.tools.tools.handler;

import org.apache.commons.lang3.StringUtils;


public class HandlerKey {

    public static String key(String group, String jobName, String bizKey) {
        StringBuffer sb = new StringBuffer();
        sb.append(group).append("#")
                .append(jobName);
        if (StringUtils.isNotBlank(bizKey)) {
            sb.append("#").append(bizKey);
        }
        return sb.toString();
    }
}
