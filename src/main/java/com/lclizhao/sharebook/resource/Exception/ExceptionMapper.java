package com.lclizhao.sharebook.resource.Exception;/**
 * Created by lizhaoz on 2015/11/30.
 */

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-29（创建日期）
 * @Description:添加切面被自动探测 当响应中抛出了AppException异常的是偶会被拦截并补充HTTP
 * 码和异常消息
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<AppException>{
    public Response toResponse(AppException ex){
        return Response.status(ex.getStatus())
                .entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
