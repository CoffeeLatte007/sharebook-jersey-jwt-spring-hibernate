package com.lclizhao.sharebook.service.impl;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.dao.BookDao;
import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.resource.Exception.AppException;
import com.lclizhao.sharebook.service.BookService;
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
@Service("bookService")
//设置统一的只读事务,具体的在下面细粒度配置
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{
    private final Logger logger= LogManager.getLogger(BookServiceImpl.class.getName());
    @Autowired
    BookDao bookDao;
    @Override
    public List<Book> findBookBylist(Book book, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Book getByid(String bookId) {
        return bookDao.getById(bookId);
    }

    //针对自定义的图书和非自定义的图书
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public Book save(Book book) {
        if(book.getiSBN()==null){
            bookDao.save(book);
        }
        else{
            String condition="";
            //参数列表
            List<Object> paramsList = new ArrayList<Object>();
            if(StringUtils.isNotBlank(book.getiSBN())){
                condition += " and o.iSBN=? ";
                paramsList.add(book.getiSBN());
            }
           List<Book> list= bookDao.findCollectionByConditionNoPage(condition, paramsList.toArray(), null);

            if(list!=null&&list.size()>0){
                return list.get(0);
            }
            else {
                bookDao.save(book);
            }
        }
        return book;
    }
}
