
package tk.yuqi.tools.tools.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * 类 BeanCopyUtil 的实现描述：BeanCopyUtil
 *
 * @since 2018/5/8
 */
public class BeanCopyUtil {

    private static Map<Integer, Copier> copierMap = new HashMap<>();

    static {
        copierMap.put(CopyMode.COPY_NOT_NULL_MODE, new NotNullCopier());
        copierMap.put(CopyMode.OVER_WRITE_MODE, new OverWriteCopier());
    }

    public static <S, D> void copy(S src, D dst, int mode) {
        if (src == null) {
            return;
        }
        Copier copier = copierMap.get(mode);
        if (copier == null) {
            throw new IllegalArgumentException("copy mode error. mode=" + mode);
        }
        copier.copy(src, dst);
    }

    public static <S, D> D copy(S src, Class<? extends D> cls, int mode) throws Exception {
        try {

            if (src == null) {
                return null;
            }
            D dst = cls.newInstance();
            copy(src, dst, mode);
            return dst;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static <S, D> List<D> copyCollections(Collection<S> srcList, Class<D> cls, int mode) throws Exception {

        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }

        List<D> result = new ArrayList<>();
        for (S src : srcList) {
            try {
                D dst = cls.newInstance();
                copy(src, dst, mode);
                result.add(dst);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        return result;
    }

    /**
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        copy(src, target, CopyMode.OVER_WRITE_MODE);
    }
}
