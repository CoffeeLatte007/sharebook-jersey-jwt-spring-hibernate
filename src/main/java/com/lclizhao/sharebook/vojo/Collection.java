package com.lclizhao.sharebook.vojo;/**
 * Created by lizhaoz on 2015/12/4.
 */

import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.daomain.User_Book;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:书的收藏(返回包含书)
 */
@XmlRootElement
public class Collection implements Serializable {
    @XmlElement(name="user_book")
    private User_Book user_book;
    @XmlElement(name="book")
    private Book book;
    public Collection(){
        
    }
    public Collection(User_Book user_book, Book book) {
        this.user_book = user_book;
        this.book = book;
    }

    public User_Book getUser_book() {
        return user_book;
    }

    public void setUser_book(User_Book user_book) {
        this.user_book = user_book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
