package com.lclizhao.sharebook.dao.impl;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.dao.BookDao;
import com.lclizhao.sharebook.daomain.Book;
import org.springframework.stereotype.Repository;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@Repository("bookDao")
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {
}
