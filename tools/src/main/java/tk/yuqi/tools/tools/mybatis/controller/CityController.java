/*
 * Copyright 1999-2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 * @author zhizhou
 * @date 2018/07/19
 */
package tk.yuqi.tools.tools.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.yuqi.tools.tools.mybatis.dao.CityDao;
import tk.yuqi.tools.tools.mybatis.domain.City;

/**
 * 类 CityController 的实现描述：CityController
 *
 * @author <a href="mailto:zhizhou.yq@cainiao.com">知周</a>
 * @since 2018/7/19
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityDao cityDao;

    @RequestMapping(value="/getCityByState/{state}")
    public City getCityByState( @PathVariable String state){
        return cityDao.findByState(state);
    }
}
