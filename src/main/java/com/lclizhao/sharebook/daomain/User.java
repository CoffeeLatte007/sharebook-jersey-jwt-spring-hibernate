package com.lclizhao.sharebook.daomain;/**
 * Created by lizhaoz on 2015/11/28.
 */

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-28（创建日期）
 * @Description:user的实体类
 */
@Entity
@Table(name="user")
public class User implements Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "userID",unique = true,nullable = false,length = 32)
    private String userID;
    @Column(name = "telphone",nullable = false,length = 30)
    private String telphone;
    @Column(name = "password",nullable = false,length = 30)
    private String password;//变化成md5
    @Column(name = "email",nullable = true,length = 32)
    @Email
    private String email;
    @Column(name = "nickName",nullable = true,length = 32)
    private String nickName;
    @Column(name = "headUrl",nullable = true,length = 100)
    private String headUrl;
    @Column(name = "age")
    private int age;


    @Temporal(TemporalType.DATE)
    @Column(name = "regDate",nullable = false)
    private Date regDate;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }


}
