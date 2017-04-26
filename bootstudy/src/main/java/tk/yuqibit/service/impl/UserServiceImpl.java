/*
 * Project: boot
 * 
 * File Created at 2016年12月21日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tk.yuqibit.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tk.yuqibit.dao.UserDao;
import tk.yuqibit.domain.User;
import tk.yuqibit.service.UserService;

/**
 * @Type UserServiceImpl.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月21日 下午5:00:23
 * @version 
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void create(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void create(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        create(user);
    }

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月21日 yuqi creat
 */
