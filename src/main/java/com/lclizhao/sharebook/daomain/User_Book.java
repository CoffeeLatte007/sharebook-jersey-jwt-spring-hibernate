package com.lclizhao.sharebook.daomain;/**
 * Created by lizhaoz on 2015/11/29.
 */


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@Entity
@Table(name = "user_book")
public class User_Book {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "ubId",unique = true,nullable = false,length = 32)
    private String ubId;
    @Column(name="bookId",nullable = false,length = 32)
    private String bookId;
    @Column(name="userId",nullable = false,length = 32)
    private String userId;
    @Temporal(TemporalType.DATE)
    @Column(name="date",nullable = false)
    private Date date;
    @Column(name="rating")
    private int rating=5;//设置默认为5
    @Column(name = "status",length = 10)
    private String status;
    @Column(name ="privcy",length = 10)
    private String privcy;
    @Column(name = "comment",length = 350)
    private String comment;
    public String getUbId() {
        return ubId;
    }

    public void setUbId(String ubId) {
         this.ubId = ubId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPrivcy() {
        return privcy;
    }

    public void setPrivcy(String privcy) {
        this.privcy = privcy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
