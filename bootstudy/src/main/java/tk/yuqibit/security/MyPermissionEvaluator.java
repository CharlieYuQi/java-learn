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

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Type MyPermissionEvaluator.java
 * @Desc 
 * @author yuqi
 * @date 2016年12月20日 下午3:44:00
 * @version 
 */
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private RoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
//        String username = authentication.getName();
//        Login login = loginService.findByUsername(username).get();
//        return roleService.authorized(login.getId(), targetDomainObject.toString(), permission.toString());
          return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // not supported
        return false;
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