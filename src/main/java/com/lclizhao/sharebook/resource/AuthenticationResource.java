package com.lclizhao.sharebook.resource;

/**
 * Created by lizhaoz on 2015/11/30.
 */

//~--- non-JDK imports --------------------------------------------------------

import com.lclizhao.sharebook.daomain.Token;
import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.service.UserService;
import com.lclizhao.sharebook.utils.KeyUtil;
import com.lclizhao.sharebook.utils.TokenUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

//~--- JDK imports ------------------------------------------------------------

import java.util.Calendar;
import java.util.Date;

import javax.annotation.security.PermitAll;

import javax.servlet.ServletContext;

import javax.validation.constraints.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 * 验证资源类
 */
@PermitAll()
@Path("/authentication")
public class AuthenticationResource {
    private final static Logger logger = LogManager.getLogger(AuthenticationResource.class.getName());
    @Autowired
    UserService                 userService;
    @Context
    ServletContext              context;

    /**
     * author：Lizhao
     * Date:15/12/12
     * version:1.0
     *
     * @param username
     * @param password
     *
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")

    // 进入验证方法的接口
    public Response authenticateUser(@NotNull
    @FormParam("telphone") String username, @NotNull
    @FormParam("password") String password) {

        // 设置这个token的生命时间
        Date expiry = getExpiryDate(30 * 24 * 60);    // 30天的有效日期

        // 验证账号密码是否正确
        User user = authenticate(username, password);

        //使用Token工具类得到token，生成的策略是利用用户的姓名，到期时间，和私钥
        //我这里使用的时Key key =MacProvider.generateKey(SignatureAlgorithm.HS512);
        //HS512签名算法，必须保存生成的这个key到硬盘上，不然下次会出错,因为是hash算法，所以会变
        //这个私钥可以理解为一把锁孔，可以依据这个锁孔来生成钥匙也就是token，但要进入这个门必须要匹配这个锁孔
        String jwtString = TokenUtil.getJWTString(username, expiry, KeyUtil.getKey(context));
        //这是token的实体化类，用来返回给用户
        Token  token     = new Token();

        token.setAuthToken(jwtString);
        token.setExpires(expiry);

        return Response.ok(token).build();
    }

    /**
     * author：Lizhao
     * Date:15/12/12
     * version:1.0
     *
     * @param minutes
     *
     * @return
     */
    private Date getExpiryDate(int minutes) {

        // 根据当前日期，来得到到期日期
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }

    /**
     * author：Lizhao
     * Date:15/12/12
     * version:1.0
     *
     * @param username
     * @param password
     *
     * @return
     *
     * @throws NotAuthorizedException
     * 在这个方法中实现验证用户账号密码，如果错误就抛出未验证信息，如果正确就返回一个用户
     */
    private User authenticate(String username, String password) throws NotAuthorizedException {
        User user = null;

        user = userService.findUserByTel(username);

        if (user == null) {
            logger.info("Invalid username '" + username + "' ");

            throw new NotAuthorizedException("Invalid telpone '" + username + "' ");
        }

        // we need to actually test the Hash not the password, we should never store the password in the database.
        if (user.getPassword().equals(password)) {
            logger.info("USER AUTHENTICATED");
        } else {
            logger.info("USER NOT AUTHENTICATED");

            throw new NotAuthorizedException("Invalid username or password");
        }

        return user;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
