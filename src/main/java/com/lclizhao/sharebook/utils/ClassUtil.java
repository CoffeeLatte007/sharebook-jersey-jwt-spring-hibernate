package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/11/26.
 */

import java.lang.reflect.ParameterizedType;

/**
 * @Name:ClassUtil
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 * 返回类的Class对象，防止泛型擦除
 */
public class ClassUtil {
    public  static Class getActualType(Class entity){
        ParameterizedType parameterizedType= (ParameterizedType) entity.getGenericSuperclass();
        Class entityClass=(Class)parameterizedType.getActualTypeArguments()[0];
        return entityClass;
    }
}
