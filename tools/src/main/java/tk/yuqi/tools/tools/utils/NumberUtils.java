
package tk.yuqi.tools.tools.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 类 NumberUtils 的实现描述：NumberUtils
 *
 * @since 2018/7/16
 */
public class NumberUtils {
    public static Double nullableAdd(Double a, Double b) {
        if (a == null && b == null) {
            return null;
        }
        if (a != null && b != null) {
            return a + b;
        }
        if (a != null) {
            return a;
        }
        return b;
    }

    public static Integer nullableAdd(Integer a, Integer b) {
        if (a == null && b == null) {
            return null;
        }
        if (a != null && b != null) {
            return a + b;
        }
        if (a != null) {
            return a;
        }
        return b;
    }

    public static Long nullableAdd(Long a, Long b) {
        if (a == null && b == null) {
            return null;
        }
        if (a != null && b != null) {
            return a + b;
        }
        if (a != null) {
            return a;
        }
        return b;
    }

    public static Long nullableAdd(Long a, Integer b) {
        if (a == null && b == null) {
            return null;
        }
        if (a != null && b != null) {
            return a + b;
        }
        if (a != null) {
            return a;
        }
        return Long.valueOf(b);
    }

    public static String doubleToString(Double value, int scale) {
        DecimalFormat format = new DecimalFormat();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(3);
        return format.format(value);
    }

    public static String divide(Long a, Integer b) {
        if (a == null || b == null) {
            return "";
        }
        DecimalFormat df2 = new DecimalFormat("##.###");
        BigDecimal number = new BigDecimal(a);
        BigDecimal divinum = new BigDecimal(b);
        return df2.format(number.divide(divinum).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public static String divide(Double a, Integer b) {
        if (a == null || b == null) {
            return "";
        }
        DecimalFormat df2 = new DecimalFormat("##.###");
        BigDecimal number = new BigDecimal(a);
        BigDecimal divinum = new BigDecimal(b);
        return df2.format(number.divide(divinum).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new JSONObject()));
    }
}
