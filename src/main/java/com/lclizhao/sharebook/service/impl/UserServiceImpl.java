package com.lclizhao.sharebook.service.impl;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.dao.BookDao;
import com.lclizhao.sharebook.dao.UserDao;
import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.service.BookService;
import com.lclizhao.sharebook.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name:BookServiceImpl
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@Service("userService")
//设置统一的只读事务,具体的在下面细粒度配置
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final Logger logger= LogManager.getLogger(UserServiceImpl.class.getName());
    @Autowired
    UserDao userDao;

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public User save(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    public User findUserByTel(String tel) {
        String condition="";
        //参数列表
        List<Object> paramsList = new ArrayList<Object>();
        if(StringUtils.isNotBlank(tel)){
            condition += " and o.telphone=? ";
            paramsList.add(tel);
        }
        Object []params =paramsList.toArray();
        List<User> list=userDao.findCollectionByConditionNoPage(condition, params, null);
        User user=null;
        if (list!=null&&list.size()>0){
            user=list.get(0);
        }
        return user;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public User updateUser(User user) {
        if(user!=null){
            userDao.update(user);
        }

        return user;
    }


}
