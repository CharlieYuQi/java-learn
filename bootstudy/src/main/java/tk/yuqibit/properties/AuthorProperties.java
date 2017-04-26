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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Type AuthorProperties.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月22日 下午2:41:49
 * @version 
 */
@ConfigurationProperties(prefix = "author", locations = "classpath:author.properties")
public class AuthorProperties {

    private String name;
    
    private String email;
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
