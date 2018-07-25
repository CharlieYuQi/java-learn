package tk.yuqi.tools.tools.copy;

import java.beans.PropertyDescriptor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 */
public class BeanInfoCache {

    private static ConcurrentMap<Class, BeanInfoResult> beanInfoCache = new ConcurrentHashMap<>();

    public static PropertyDescriptor[] getPropertyDescriptors(Class cls) {

        BeanInfoResult result = beanInfoCache.get(cls);
        if (result == null) {
            result = new BeanInfoResult(cls);
            beanInfoCache.put(cls, result);
        }
        return result.getPropertyDescriptors();
    }

    public static PropertyDescriptor getPropertyDescriptor(Class cls, String name) {

        BeanInfoResult result = beanInfoCache.get(cls);
        if (result == null) {
            result = new BeanInfoResult(cls);
            beanInfoCache.put(cls, result);
        }
        return result.getPropertyDescriptor(name);
    }

}
