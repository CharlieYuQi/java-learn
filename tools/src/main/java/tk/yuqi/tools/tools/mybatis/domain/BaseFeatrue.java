
package tk.yuqi.tools.tools.mybatis.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;

/**
 * 类 BaseFeatrue 的实现描述：BaseFeatrue
 *
 * @since 2018/7/19
 */
public class BaseFeatrue {
    private Map<String, String> featureMap = new HashMap<>();
    /**
     * 扩展字段
     */
    private String feature;

    public void setFeature(String feature) {
        this.feature = feature;
        if (StringUtils.isNotBlank(feature)) {
            featureMap = JSON.parseObject(feature, Map.class);
        }
    }

    public void addFeature(String key, String value) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        this.featureMap.put(key, value);
        this.feature = JSON.toJSONString(featureMap);
    }

    public void removeFeature(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        this.featureMap.remove(key);
        this.feature = JSON.toJSONString(featureMap);
    }

    public Map<String, String> getFeatureMap() {
        return featureMap;
    }

    public void setFeatureMap(Map<String, String> featureMap) {
        this.featureMap = featureMap;
        this.feature = JSON.toJSONString(featureMap);
    }

    public String getFeature(String key) {
        return featureMap.get(key);
    }

    /**
     * getter for column 扩展字段
     */
    public String getFeature() {
        return this.feature;
    }

    @Override
    public String toString() {
        return "Feature{" +
               "feature='" + feature + '\'' +
               '}';
    }
}
