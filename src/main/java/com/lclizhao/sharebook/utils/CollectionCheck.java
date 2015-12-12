package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/12/2.
 */

import com.lclizhao.sharebook.daomain.User_Book;
import com.lclizhao.sharebook.resource.Exception.AppException;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-12-2（创建日期）
 * @Description:
 */
public class CollectionCheck {
    public static void check(User_Book user_book) throws AppException{
        if(user_book.getStatus()!=null){
            String status=user_book.getStatus().trim();
            if(!("read".equals(status)||"wish".equals(status)||"reading".equals(status))){
                String message=user_book.getStatus()+" is not a correct satus(read,reading,wish)";
                throw new AppException(400,2006,"link",message,message);
            }
        }
        if(user_book.getComment()!=null&&user_book.getComment().length()>350){
            throw new AppException(400,2007,"link","the Comment length >350","the Comment length >350");
        }

        if(user_book.getPrivcy()!=null)
        {
            String privcy=user_book.getPrivcy().trim();
            if(!(privcy.equals("private")||privcy.equals("public"))){
                String message=privcy+" is not a correct privcy(private,public)";
                throw new AppException(400,2008,"link",message,message);
            }
        }
        //设置默认public
        else {
            user_book.setPrivcy("public");
        }
    }
}
