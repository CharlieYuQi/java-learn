/*
 * Project: boot
 * 
 * File Created at 2017年1月20日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tk.yuqibit.lambda;

import java.util.LinkedList;
import java.util.List;


/**
 * @Type LambdaStudy.java
 * @Desc 
 * @author yuqi
 * @date 2017年1月20日 下午2:53:19
 * @version 
 */
public class LambdaStudy {

    public Runnable r2 = new Runnable() {
        public void run() {
            System.out.println(this);
            System.out.println(toString());
        }
    };

    public Runnable r3 = new Runnable() {
        public void run() {
            System.out.println(LambdaStudy.this);
            System.out.println(LambdaStudy.this.toString());
        }
    };
    public Runnable r = () -> {
        System.out.println(this);
        System.out.println(toString());
    };

    public String toString() {
        return "Hello's custom toString()";
    }

    public static void main(String... args) {
        LambdaStudy h = new LambdaStudy();
        h.r3.run();
    }
    
    List<String> tt = new LinkedList<String>();
    
}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2017年1月20日 yuqi creat
 */
