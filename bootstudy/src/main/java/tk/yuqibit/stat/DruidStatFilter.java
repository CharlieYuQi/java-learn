/*
 * Project: boot
 * 
 * File Created at 2016年12月19日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tk.yuqibit.stat;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Type DruidStatFilter.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月19日 下午4:12:25
 * @version 
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", 
    initParams = { 
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") // 忽略资源
    })
public class DruidStatFilter extends WebStatFilter {

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月19日 yuqi creat
 */
