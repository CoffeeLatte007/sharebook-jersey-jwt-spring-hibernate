package com.lclizhao.sharebook.resource;/**
 * Created by lizhaoz on 2015/11/28.
 */

import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.resource.Exception.AppException;
import com.lclizhao.sharebook.service.UserService;
import com.lclizhao.sharebook.utils.UserCheck;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-28（创建日期）
 * @Description:User的资源类
 */
@PermitAll
@Path("user")
public class UserResource {
    private final org.apache.logging.log4j.Logger logger= LogManager.getLogger(UserResource.class.getName());
    @Autowired
    UserService userService;
    /**
     * AJAX查询该账号是否被注册
     *
     * @param tel
     * @return String 如果被注册就返回1，未被注册就返回0返回的是text/html
     *
     */
    @GET
    public String checkLoginRegist(@QueryParam("tel") String tel){
        if(tel!=null){
           if(userService.findUserByTel(tel)==null) {

               return "0";
           }
            return "1";
        }
        return "0";
    }
    /**
     * 注册该账号
     *
     * @param user
     * @return User 如果被注册或者其他情况就返回没有user设置telphone为“注册失败”，
     * 未被注册且注册成功就返回该user
     *
     */
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("regist")
    public Response registUser(User user) throws AppException{
        String message="";
        UserCheck.check(user);
        if(userService.findUserByTel(user.getTelphone())==null){
            user.setRegDate(new Date());
            user=userService.save(user);
            user.setPassword(null);
            return Response.status(200)
                    .entity(user)
                    .build();
        }
        else {
            message="user has regeisted";
            logger.error(message);
            throw new AppException(409,1005,"link",message,message);
        }
    }

}
