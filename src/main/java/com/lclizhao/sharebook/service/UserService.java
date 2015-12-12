package com.lclizhao.sharebook.service;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.daomain.User;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 * 书的Service层接口
 */
public interface UserService {
    /**
     * 保存用户
     *
     * @param user
     */
    public User save(User user);

    /**
     * 通过用户的账号找到用户
     *
     * @param tel
     * 返回user
     */
    public User findUserByTel(String tel);
    /**
     *更新用户
     *
     * @param user
     */
    public User updateUser(User user);
}
