package com.lclizhao.sharebook.service.impl;

import com.lclizhao.sharebook.dao.AnnotationDao;
import com.lclizhao.sharebook.dao.CollectionDao;
import com.lclizhao.sharebook.daomain.Annotation;
import com.lclizhao.sharebook.daomain.User_Book;
import com.lclizhao.sharebook.service.AnnotationService;
import com.lclizhao.sharebook.utils.FileUtils;
import com.lclizhao.sharebook.utils.URL;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name:AnnotationServiceImpl.java
 * @Create Date: 15/12/07（创建日期）
 * @Description:
 *
 * @version        1.0, 15/12/07
 * @author         Lizhao    
 */
@Service("annotationService")
//设置统一的只读事务,具体的在下面细粒度配置
@Transactional(readOnly = true)
public class AnnotationServiceImpl implements AnnotationService {
    @Autowired
    AnnotationDao annotationDao;
    @Autowired
    CollectionDao collectionDao;
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public Annotation saveAnnotion(InputStream fileInputStream, FormDataContentDisposition disposition, String content, String page_no, String digest,String ubId) {
        Annotation annotation=new Annotation();
        annotation.setUbId(ubId);
        annotation.setContent(content);
        annotation.setPage_No(Integer.parseInt(page_no));
        if(digest==null){
            String path=FileUtils.annotionImageUpload(fileInputStream, disposition, "annotionImage");
            annotation.setDigestPhoto(URL.DIGEST+path);

        }
        else {
            annotation.setDigest(digest);
        }
        annotationDao.save(annotation);
        return  annotation;
    }

    @Override
    public boolean getbooleanById(String annotationId, String userId) {
        Annotation annotation=annotationDao.getById(annotationId);
        if(annotation!=null){
            User_Book user_book=collectionDao.getById(annotation.getUbId());
            if(user_book!=null){
                if(user_book.getUserId().equals(userId)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public Annotation updateAnnotion(InputStream fileInputStream, FormDataContentDisposition disposition, String content, String page_no, String digest, String annotationId) {
        Annotation annotation=annotationDao.getById(annotationId);
        annotation.setContent(content);
        annotation.setPage_No(Integer.parseInt(page_no));
        if(digest==null){
            String path=FileUtils.annotionImageUpload(fileInputStream, disposition, "annotionImage");
            annotation.setDigestPhoto(URL.DIGEST+path);

        }
        else {
            annotation.setDigest(digest);
        }
        return annotation;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public void delete(String annotationId) {
        annotationDao.delete(annotationDao.getById(annotationId));
    }

    @Override
    public List<Annotation> getAnnotationByCollection(String ubId) {
        String condition="";
        //参数列表
        List<Object> paramsList = new ArrayList<Object>();
        if(StringUtils.isNotBlank(ubId)){
            condition += " and o.ubId=? ";
            paramsList.add(ubId);
        }
        Map<String,String> order=new LinkedHashMap<String,String>();
        order.put(" o.page_No "," desc ");
        List<Annotation> list= annotationDao.findCollectionByConditionNoPage(condition, paramsList.toArray(),order );
        return list;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
