package tk.yuqi.tools.tools.copy;

import java.beans.PropertyDescriptor;

/**
 */
public class OverWriteCopier<S, D> implements Copier<S, D> {

    private StrategyCopier<S, D> copier = new StrategyCopier<>(new CopyStrategy() {
        @Override
        public boolean match(PropertyDescriptor srcPd, PropertyDescriptor targetPd, Object srcValue) {
            return true;
        }
    });

    @Override
    public void copy(S source, D dest) {
        copier.copy(source, dest);
    }
}
