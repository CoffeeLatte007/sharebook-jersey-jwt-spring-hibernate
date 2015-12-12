package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/11/30.
 */

import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.resource.Exception.AppException;
import org.apache.logging.log4j.LogManager;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-30（创建日期）
 * @Description:
 */
public class UserCheck {
    private static final org.apache.logging.log4j.Logger logger= LogManager.getLogger(UserCheck.class.getName());
    public static void check(User user) throws AppException{
        String message="";
        if (user.getTelphone()==null){
            message="usertel is null";
            logger.error(message);
            throw new AppException(200,1001,"link",message,message);
        }
        if(!TelPhoneMatch.isTel(user.getTelphone())){
            message="usertel is not a tel";
            logger.error(message);
            throw new AppException(200,1002,"link",message,message);
        }
        if(user.getPassword()==null){
            message="userpassword is null";
            logger.error(message);
            throw new AppException(200,1003,"link",message,message);
        }
        if (!TelPhoneMatch.isPassword(user.getPassword())){
            message="userpassword is not a password";
            logger.error(message);
            throw new AppException(200,1004,"link",message,message);
        }
    }
}
