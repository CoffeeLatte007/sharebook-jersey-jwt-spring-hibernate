package com.lclizhao.sharebook.service;

import com.lclizhao.sharebook.daomain.Annotation;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.util.List;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 15/12/07（创建日期）
 * @Description:
 * @PathParam
 *
 * @version        1.0, 15/12/07
 * @author         Lizhao    
 */
public interface AnnotationService {

    Annotation saveAnnotion(InputStream fileInputStream, FormDataContentDisposition disposition, String content, String page_no, String digest,String ubId);

    boolean getbooleanById(String annotationId, String userId);

    Annotation updateAnnotion(InputStream fileInputStream, FormDataContentDisposition disposition, String content, String page_no, String digest, String annotationId);

    void delete(String annotationId);

    List<Annotation> getAnnotationByCollection(String ubId);
}


//~ Formatted by Jindent --- http://www.jindent.com
