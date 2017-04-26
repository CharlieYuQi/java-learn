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
package tk.yuqibit.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import tk.yuqibit.domain.User;
import tk.yuqibit.service.UserService;

/**
 * @Type MyUserDetailsService.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月20日 下午3:41:04
 * @version 
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    
    private static Logger logger = LogManager.getLogger(MyUserDetailsService.class);

//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("load user:"+username);
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }

        User user = userService.findByName(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

        Set<GrantedAuthority> authorities = new HashSet<>();
//        roleService.getRoles(login.getId()).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(),
                true,//是否可用
                true,//是否过期
                true,//证书不过期为true
                true,//账户未锁定为true
                authorities);
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