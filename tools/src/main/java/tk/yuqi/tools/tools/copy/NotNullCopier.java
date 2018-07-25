package tk.yuqi.tools.tools.copy;

import java.beans.PropertyDescriptor;

/**
 */
public class NotNullCopier<S, D> implements Copier<S, D> {

    private StrategyCopier<S, D> copier = new StrategyCopier<S, D>(new CopyStrategy() {
        @Override
        public boolean match(PropertyDescriptor srcPd, PropertyDescriptor targetPd, Object srcValue) {
            if (srcValue == null) {
                return false;
            }
            return true;
        }
    });

    @Override
    public void copy(S source, D target) {
        copier.copy(source, target);
    }
}
