/*
 * Project: boot
 * 
 * File Created at 2016年12月22日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tk.yuqibit.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Type MyConfig.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月22日 下午2:52:50
 * @version 
 */
@Configuration
@EnableConfigurationProperties(AuthorProperties.class)
@PropertySource(value = { "classpath:author.properties" })
public class MyConfig {

    
    @Value("${test}")
    private String test;
    
    @Value("${author.version}")
    private String version;
    
    @Autowired
    private AuthorProperties authorProperties;
    
    public String getAuthorName() {
        return authorProperties.getName();
    }

    public String getTest() {
        return test;
    }
    
    public String getVersion() {
        return version;
    }
}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月22日 yuqi creat
 */