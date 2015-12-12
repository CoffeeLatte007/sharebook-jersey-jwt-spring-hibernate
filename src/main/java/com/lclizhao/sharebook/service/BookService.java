package com.lclizhao.sharebook.service;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.resource.Exception.AppException;

import java.util.List;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 * 书的Service层接口
 */
public interface BookService {
    /**
     * 保存一本图书
     *
     * @param book
     */
    public Book save(Book book);
    /**
     * 查询图书
     *
     * @param book 图书根据ISBN，书名，作者，查询
     *
     */
    public List<Book> findBookBylist(Book book,int pageNo,int pageSize);

    Book getByid(String bookId) ;
}
