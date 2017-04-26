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
package tk.yuqibit.hello;

/**
 * @Type SampleController.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月19日 下午2:08:50
 * @version 
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @RequestMapping("/sample")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月19日 yuqi creat
 */