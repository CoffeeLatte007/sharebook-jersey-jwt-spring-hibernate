package com.lclizhao.sharebook.dao.impl;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.dao.CollectionDao;
import com.lclizhao.sharebook.dao.UserDao;
import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.daomain.User_Book;
import org.springframework.stereotype.Repository;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@Repository("collectionDao")
public class CollectionDaoImpl extends BaseDaoImpl<User_Book> implements CollectionDao {
}
