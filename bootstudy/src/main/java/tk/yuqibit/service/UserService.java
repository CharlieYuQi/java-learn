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
package tk.yuqibit.service;

import java.util.Optional;

import tk.yuqibit.domain.User;

/**
 * @Type UserService.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月21日 下午4:56:08
 * @version 
 */
public interface UserService {
    Optional<User> findByName(String name);
    void create(User user);
    void create(String name, String password);

}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月21日 yuqi creat
 */