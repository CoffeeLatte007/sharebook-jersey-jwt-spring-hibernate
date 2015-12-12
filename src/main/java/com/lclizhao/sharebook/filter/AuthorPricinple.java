package com.lclizhao.sharebook.filter;/**
 * Created by lizhaoz on 2015/11/30.
 */

import java.security.Principal;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
public class AuthorPricinple implements Principal {
    private String name;

    public AuthorPricinple(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
