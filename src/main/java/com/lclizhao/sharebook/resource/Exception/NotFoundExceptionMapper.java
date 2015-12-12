package com.lclizhao.sharebook.resource.Exception;/**
 * Created by lizhaoz on 2015/11/30.
 */

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-29（创建日期）
 * @Description:
 */
@Provider
public class NotFoundExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<NotFoundException> {
    public Response toResponse(NotFoundException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
