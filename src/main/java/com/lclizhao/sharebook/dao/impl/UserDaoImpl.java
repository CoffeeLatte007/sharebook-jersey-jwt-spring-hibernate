package com.lclizhao.sharebook.dao.impl;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.dao.BookDao;
import com.lclizhao.sharebook.dao.UserDao;
import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.daomain.User;
import org.springframework.stereotype.Repository;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
}
