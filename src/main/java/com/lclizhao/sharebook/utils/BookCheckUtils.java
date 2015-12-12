package com.lclizhao.sharebook.utils;

import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.resource.Exception.AppException;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-30（创建日期）
 * @Description:
 */
public class BookCheckUtils {
    public static void check(Book book) throws AppException{
        String message="";
        if (book.getBookName()==null){
            message="bookName is null";
            throw new AppException(400,2001,"link",message,message);
        }
        if (book.getBookName()!=null&&book.getBookName().length()>30){
            message="bookName is too long";
            throw new AppException(400,2002,"link",message,message);
        }
        if ((book.getAuthor()!=null&&book.getAuthor().length()>20)
                ||(book.getCoverUrl()!=null&&book.getCoverUrl().length()>255)
                ||(book.getiSBN()!=null&&(book.getiSBN().length()>20||book.getiSBN().trim().length()<10))
                ||(book.getPublisher()!=null&&book.getPublisher().length()>30)){
            message="The format of parameter is error";
            throw new AppException(400,2003,"link",message,message);
        }
    }
}
