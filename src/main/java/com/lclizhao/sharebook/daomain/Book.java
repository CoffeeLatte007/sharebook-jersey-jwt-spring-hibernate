package com.lclizhao.sharebook.daomain;/**
 * Created by lizhaoz on 2015/11/26.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @Name:Book
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:书的持久化类
 */
@Entity
@Table(name="book")
public class Book implements Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "bookId",unique = true,nullable = false,length = 32)
    private String bookID;
    @Column(name = "bookName",nullable = false,length = 30)
    private String bookName;
    @Column(name = "author",nullable = true,length = 20)
    private String author;
    @Column(name ="coverUrl",nullable = true)
    private String coverUrl;
    @Column(name = "iSBN",nullable = true,length = 20)
    private String iSBN;
    @Column(name = "publisher",nullable=true,length = 30)
    private String publisher;



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
