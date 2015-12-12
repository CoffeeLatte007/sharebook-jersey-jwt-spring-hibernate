package com.lclizhao.sharebook.dao.impl;

//~--- non-JDK imports --------------------------------------------------------

import com.lclizhao.sharebook.dao.AnnotationDao;
import com.lclizhao.sharebook.daomain.Annotation;
import org.springframework.stereotype.Repository;

/**
 * @Name:AnnoationDaoImpl.java
 * @Create Date: 15/12/07（创建日期）
 * @Description:
 *  笔记的DAO
 * @version        1.0, 15/12/07
 * @author         Lizhao    
 */
@Repository("annotationDao")
public class AnnotationDaoImpl extends BaseDaoImpl<Annotation> implements AnnotationDao {}


//~ Formatted by Jindent --- http://www.jindent.com
