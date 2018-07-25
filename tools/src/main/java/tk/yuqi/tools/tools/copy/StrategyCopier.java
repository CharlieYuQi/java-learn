package tk.yuqi.tools.tools.copy;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;

/**
 */
public class StrategyCopier<S, D> implements Copier<S, D> {

    private CopyStrategy copyStrategy;

    public StrategyCopier(CopyStrategy strategy) {
        this.copyStrategy = strategy;
    }

    @Override
    public void copy(S source, D target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("source and target can not be null");
        }

        PropertyDescriptor[] targetPds = BeanInfoCache.getPropertyDescriptors(target.getClass());
        if (ArrayUtils.isEmpty(targetPds)) {
            return;
        }

        for (PropertyDescriptor targetPd : targetPds) {

            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod == null) {
                continue;
            }

            PropertyDescriptor sourcePd = BeanInfoCache.getPropertyDescriptor(source.getClass(), targetPd.getName());
            if (sourcePd == null) {
                continue;
            }

            Method readMethod = sourcePd.getReadMethod();
            if (!writeMethod.getParameterTypes()[0].isAssignableFrom(readMethod.getReturnType())) {
                continue;
            }
            if (!readMethod.isAccessible()) {
                readMethod.setAccessible(true);
            }

            try {
                Object srcValue = readMethod.invoke(source);
                if (copyStrategy.match(sourcePd, targetPd, srcValue)) {
                    if (!writeMethod.isAccessible()) {
                        writeMethod.setAccessible(true);
                    }
                    writeMethod.invoke(target, srcValue);
                }

            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
