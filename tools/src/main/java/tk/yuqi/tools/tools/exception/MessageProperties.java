package tk.yuqi.tools.tools.exception;

import java.util.Map;


public class MessageProperties {
    private Map<String, String> properties;

    public MessageProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getMessage(String code) {
        return this.properties.get(code);
    }
}
