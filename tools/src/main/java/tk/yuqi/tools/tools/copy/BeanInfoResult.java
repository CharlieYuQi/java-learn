package tk.yuqi.tools.tools.copy;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

/**
 */
public class BeanInfoResult {

    private Class cls;

    private BeanInfo beanInfo;

    private PropertyDescriptor[] pds;

    private Map<String, PropertyDescriptor> propertyDescriptorCache = new HashMap<>();

    public BeanInfoResult(Class cls) {

        this.cls = cls;

        try {
            this.beanInfo = Introspector.getBeanInfo(cls);

            PropertyDescriptor[] pds = this.beanInfo.getPropertyDescriptors();
            if (ArrayUtils.isEmpty(pds)) {
                return;
            }

            this.pds = pds;
            for (PropertyDescriptor pd : pds) {
                propertyDescriptorCache.put(pd.getName(), pd);
            }
        } catch (Exception e) {
            throw new RuntimeException("get beaninfo error", e);
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        return pds;
    }

    public PropertyDescriptor getPropertyDescriptor(String name) {

        if (StringUtils.isEmpty(name)) {
            return null;
        }

        PropertyDescriptor pd = propertyDescriptorCache.get(name);
        if (pd == null) {
            // Same lenient fallback checking as in PropertyTypeDescriptor...
            pd = this.propertyDescriptorCache.get(name.substring(0, 1).toLowerCase() + name.substring(1));
            if (pd == null) {
                pd = this.propertyDescriptorCache.get(name.substring(0, 1).toUpperCase() + name.substring(1));
            }
        }
        return pd;
    }
}
