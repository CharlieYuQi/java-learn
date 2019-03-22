/*
 * Copyright 1999-2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 * @author zhizhou
 * @date 2018/07/27
 */
package tk.yuqi.tools.tools.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 类 TextUtils 的实现描述：TextUtils
 *
 * @author <a href="mailto:zhizhou.yq@cainiao.com">知周</a>
 * @since 2018/7/27
 */
public class TextUtils {
    public static void main(String[] args) {
        String fileName = "/Users/zhizhou/workspace/firstleg.txt";
        List<Model> models = Lists.newArrayList();
        try (BufferedReader bufferedReader = new BufferedReader(
            new FileReader(fileName))) {
            String s = null;
            int line = 0;
            while ((s = bufferedReader.readLine()) != null) {
                line++;
                String[] split = s.split("\t", -1);
                if (split == null || split.length != 6) {
                    throw new Exception("exception line:" + line);
                }

                Model model = new Model();
                if (StringUtils.isNotBlank(split[0])) {
                    model.setCountryCode(split[0].trim());
                }
                if (StringUtils.isNotBlank(split[1])) {
                    model.setPol(split[1].trim());
                }
                if (StringUtils.isNotBlank(split[2])) {
                    model.setPod(split[2].trim());
                }
                if (StringUtils.isNotBlank(split[3])) {
                    model.setTransportMode(split[3].trim());
                }
                if (StringUtils.isNotBlank(split[4]) && !"FLIGHT".equals(split[5].trim())) {
                    model.setContainerLoad(split[4].trim());
                }
                if (StringUtils.isNotBlank(split[5])) {
                    model.setResCode(split[5].trim());
                }
                if (!models.contains(model)){
                    models.add(model);
                }


            }
            Collections.sort(models, Comparator.comparing(Model::getCountryCode));
            System.out.println(JSON.toJSONString(models.size()));
            System.out.println(JSON.toJSONString(models));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Data
    public static class Model {
        @JSONField(ordinal=1)
        private String countryCode;
        @JSONField(ordinal=2)
        private String pol;
        @JSONField(ordinal=3)
        private String pod;
        @JSONField(ordinal=4)
        private String userId;
        @JSONField(ordinal=5)
        private String transportMode;
        @JSONField(ordinal=6)
        private String containerLoad;
        @JSONField(ordinal=7)
        private String resCode;

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }

            if (o == null || getClass() != o.getClass()) { return false; }

            Model model = (Model)o;

            return new EqualsBuilder()
                .append(countryCode, model.countryCode)
                .append(pol, model.pol)
                .append(pod, model.pod)
                .append(userId, model.userId)
                .append(transportMode, model.transportMode)
                .append(resCode, model.resCode)
                .append(containerLoad, model.containerLoad)
                .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                .append(countryCode)
                .append(pol)
                .append(pod)
                .append(userId)
                .append(transportMode)
                .append(resCode)
                .append(containerLoad)
                .toHashCode();
        }
    }
}
