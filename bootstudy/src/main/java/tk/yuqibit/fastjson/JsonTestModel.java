/*
 * Project: boot
 * 
 * File Created at 2016年12月23日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tk.yuqibit.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Type JsonTest.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月23日 下午2:05:56
 * @version 
 */
public class JsonTestModel {

    private String user;
    
    @JSONField(serialize = false)
    private String mail;
    
    private int age;

    
    public JsonTestModel(String user, String mail, int age) {
        this.user = user;
        this.mail = mail;
        this.age = age;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    

}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月23日 yuqi creat
 */