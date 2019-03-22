package tk.yuqi.tools.tools.exception;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ResourceBundleMessageLoader {
    private static final String basePath = "i18n/";
    private static final String resourceFileSuffix = ".properties";

    public static MessageProperties load(String fileName) {
        String resourcePath = basePath + fileName + resourceFileSuffix;
        Map<String, String> properties = loadProperties(resourcePath);

        return new MessageProperties(properties);
    }

    private static Map<String, String> loadProperties(String resourcePath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Map<Object, Object> props = new HashMap<Object, Object>();
        try {
            Enumeration<URL> resources = classLoader.getResources(resourcePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                InputStream in = url.openStream();
                Properties prop = new Properties();
                prop.load(new InputStreamReader(in, "UTF-8"));
                props.putAll(prop);
            }
        } catch (IOException e) {
            return Collections.emptyMap();
        }

        Map<String, String> errorMessageProperties = new HashMap<>(props.size());
        for (Map.Entry<Object, Object> e : props.entrySet()) {
            errorMessageProperties.put(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
        }

        return errorMessageProperties;
    }
}
