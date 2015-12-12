package com.lclizhao.sharebook.daomain;/**
 * Created by lizhaoz on 2015/12/6.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-12-6（创建日期）
 * @Description:
 * 用户笔记的POJO
 */
@Entity
@Table(name = "annotation")
public class Annotation implements Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "annotationId",unique = true,nullable = false,length = 32)
    private String annotationId;
    @Column(name ="ubId",nullable = false,length = 32)
    private String ubId;
    //书摘
    @Column(name="digest",nullable = true,length = 100)
    private String digest;
    //书摘图片地址
    @Column(name="digestPhoto",nullable = true,length = 200)
    private String digestPhoto;
    @Column(name="page_No",nullable = false)
    private Integer page_No;
    @Column(name="content",nullable = false,length = 1000)
    private String content;
    public String getAnnotation() {
        return annotationId;
    }

    public void setAnnotation(String annotation) {
        this.annotationId = annotation;
    }

    public String getUbId() {
        return ubId;
    }

    public void setUbId(String ubId) {
        this.ubId = ubId;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDigestPhoto() {
        return digestPhoto;
    }

    public void setDigestPhoto(String digestPhoto) {
        this.digestPhoto = digestPhoto;
    }

    public Integer getPage_No() {
        return page_No;
    }

    public void setPage_No(Integer page_No) {
        this.page_No = page_No;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
