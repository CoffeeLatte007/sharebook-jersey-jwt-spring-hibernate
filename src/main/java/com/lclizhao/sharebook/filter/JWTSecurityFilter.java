package com.lclizhao.sharebook.filter;/**
 * Created by lizhaoz on 2015/11/30.
 */

import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.service.UserService;
import com.lclizhao.sharebook.utils.KeyUtil;
import com.lclizhao.sharebook.utils.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@Provider
@Priority(Priorities.AUTHENTICATION)//优先级最高
//实现该拦截器借口
//@Provider可以自动注册
public class JWTSecurityFilter implements ContainerRequestFilter{
    final static Logger logger = LogManager.getLogger(JWTSecurityFilter.class.getName());
    @Autowired
    UserService userservice;
//    @Context
//    Key key;
    @Context
    ServletContext context;
    @Inject
    javax.inject.Provider<UriInfo> uriInfo;
    public static String extractJwtTokenFromAuthorizationHeader(String auth) {
        //Replacing "Bearer Token" to "Token" directly
        return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "").replace(" ", "");
    }
  //重写验证过滤器
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        //获取本地的私钥
        Key key= KeyUtil.getKey(context);
        //得到访问的方法 例如GET,POST
        String method = containerRequestContext.getMethod().toLowerCase();
        //得到访问路径
        String path = ((ContainerRequest) containerRequestContext).getPath(true).toLowerCase();
        //get application.wadl和application.wadl/xsd0.xsd不需要验证，post验证过滤,注册过滤。
        if (("get".equals(method) && ("application.wadl".equals(path) || "application.wadl/xsd0.xsd".equals(path)))
                || ("post".equals(method) &&( "authentication".equals(path)||"regist".equals(path)))||("get".equals(method) && "user".equals(path))) {
            // pass through the filter.
            containerRequestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo,new AuthorPricinple("pass"), new String[]{"pass"}));
            return;
        }
        //获取头信息中的token
        String authorizationHeader = ((ContainerRequest) containerRequestContext).getHeaderString("auth_token");
        //如果token为空抛出
        if (authorizationHeader == null) {

            throw new WebApplicationException(Response.Status.UNAUTHORIZED);//抛出未认证的错误
        }
        //把Bear Token换成Token
        String strToken=extractJwtTokenFromAuthorizationHeader(authorizationHeader);
        if (TokenUtil.isValid(strToken,key)){
            String name=TokenUtil.getName(strToken,key);//反解出Name
            String[] roles=TokenUtil.getRoles(strToken,key);//反解出角色
            int version=TokenUtil.getVersion(strToken,key);//得到版本
            if(name !=null&&roles.length!=0&&version!=-1){
                User user=userservice.findUserByTel(name);
                if(user!=null){
                    containerRequestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo,new AuthorPricinple(name), new String[]{"user"}));
                    return;
                }
                else{
                    logger.info("User not found " + name);
                }
            }
            else {
                logger.info("name, roles or version missing from token");
            }
        }
        else {
            logger.info("token is invalid");

        }
        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }
}
