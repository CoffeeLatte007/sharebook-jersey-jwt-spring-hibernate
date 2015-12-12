package com.lclizhao.sharebook.resource.Exception;/**
 * Created by lizhaoz on 2015/11/30.
 */

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 * 错误消息的实体，和jersey映射
 */
@XmlRootElement
public class ErrorMessage {
    private final org.apache.logging.log4j.Logger logger= LogManager.getLogger(ErrorMessage.class.getName());
    @XmlElement(name="HTTPStatus")
    int status;
    @XmlElement(name="code")
    int code;
    @XmlElement(name="message")
    String message;
    @XmlElement(name = "link")
    String link;
    @XmlElement(name ="errorMessage")
    String errorMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public ErrorMessage(AppException ex){
        try{
            BeanUtils.copyProperties(this,ex);
        }
        catch (IllegalAccessException e){
            logger.error(e);
            e.printStackTrace();
        }
        catch (InvocationTargetException e){
            logger.error(e);
            e.printStackTrace();
        }
    }
    public ErrorMessage(NotFoundException ex){
        this.status = Response.Status.NOT_FOUND.getStatusCode();
        this.message = ex.getMessage();
        this.link = "link";
    }

    public ErrorMessage() {}


}
