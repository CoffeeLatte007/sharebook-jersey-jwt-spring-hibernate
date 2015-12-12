package com.lclizhao.sharebook.dao;/**
 * Created by lizhaoz on 2015/11/26.
 */

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Name:BaseDao
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:基础数据库操作类
 * 其他DAO继承此类获取常用的数据库操作方法，基本上你能用到的方法这里都有了，不需要自己建立DAO了。
 */
public interface BaseDao <T>{
    /**
     * 保存一个对象
     *
     * @param entity
     */
    public void save(T entity);
    /**
     * 删除一个对象
     *
     * @param entity
     */
    public void delete(T entity);
    /**
     * 保存或更新一个对象
     *
     * @param entity
     */
    public void update(T entity);
    /**
     *  通过主键获得对象
     *
     * @param id
     * @return 对象
     */
    public T getById(Serializable id);
    /**
     * 批量删除多个对象
     *
     * @param ids
     */
    public void deleteObjectByIds(Serializable... ids);
    /**
     * 查询数据（没有分页）
     *
     * @param condition 查询条件
     * @param params 查询条件参数
     * @param orderby 排序条件
     * @return List
     */
    public List<T> findCollectionByConditionNoPage(String condition,
                                                   Object[] params, Map<String, String> orderby);
    /**
     * 查询数据（没有分页）
     *
     * @param condition 查询条件
     * @param params 查询条件参数
     * @param orderby 排序条件
     * @param pageNo 要显示第几页
     * @param oageSize 要显示多少条
     * @return List
     */
    public List<T> findCollectionByConditionPage(String condition,
                                                 Object[] params, Map<String, String> orderby,int pageNo,int oageSize);
}
