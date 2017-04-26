/*
 * Project: boot
 * 
 * File Created at 2016年12月20日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tk.yuqibit.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.yuqibit.fastjson.JsonTestModel;
import tk.yuqibit.properties.MyConfig;
import tk.yuqibit.service.UserService;


/**
 * @Type HelloController.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月20日 上午10:43:13
 * @version 
 */
@Controller
public class HelloController {
    
    private static Logger logger = LogManager.getLogger(HelloController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private MyConfig myConfig;
    
    
    public HelloController() {
        logger.info("info");
        logger.error("error");
    }

    @RequestMapping("/")
    public String index() {
        try {
            userService.create("admin","123456");
            userService.create("yuqi","yuqi1234");
        } catch (Exception e) {
            // TODO: handle exception
        }
        logger.info("index log");
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/except")
    public String except() throws Exception {
        throw new Exception("test");
    }
    
    @RequestMapping("/prop")
    public String prop() throws Exception {
        System.out.println(myConfig.getAuthorName());
        System.out.println(myConfig.getTest());
        System.out.println(myConfig.getVersion());
        return "hello";
    }
    
    @RequestMapping("/json")
    @ResponseBody
    public JsonTestModel jsonTest()
    {
        return new JsonTestModel("admin", "admin@admin.com", 99);
    }

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月20日 yuqi creat
 */