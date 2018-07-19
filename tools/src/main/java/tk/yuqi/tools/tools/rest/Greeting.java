/*
 * Copyright 1999-2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 * @author zhizhou
 * @date 2018/07/19
 */
package tk.yuqi.tools.tools.rest;

import lombok.Data;

/**
 * 类 Greeting 的实现描述：Greeting
 *
 * @author <a href="mailto:zhizhou.yq@cainiao.com">知周</a>
 * @since 2018/7/19
 */
@Data
public class Greeting {
    private final long id;
    private final String content;
}
