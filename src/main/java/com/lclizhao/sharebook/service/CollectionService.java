package com.lclizhao.sharebook.service;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.daomain.User_Book;
import com.lclizhao.sharebook.resource.Exception.AppException;
import com.lclizhao.sharebook.vojo.Collection;
import com.lclizhao.sharebook.vojo.Collections;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 * 书的Service层接口
 */
public interface CollectionService {

    Collection save(User_Book user_book) throws AppException;

    User_Book getById(String ubId);

    Collection update(User_Book user_book) throws AppException ;

    void delete(String bookId, String userId) throws AppException;

    Collections getallcollectionbyuser(String userId, String status, int rating, int start, int count);

    Collection getCollection(String bookId, String userId)throws AppException;
}
