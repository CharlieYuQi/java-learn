package tk.yuqi.tools.tools.copy;

import java.beans.PropertyDescriptor;

/**
 */
public interface CopyStrategy {

    boolean match(PropertyDescriptor srcPd, PropertyDescriptor targetPd, Object srcValue);
}
