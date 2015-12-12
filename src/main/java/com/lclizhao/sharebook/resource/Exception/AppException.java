package com.lclizhao.sharebook.resource.Exception;/**
 * Created by lizhaoz on 2015/11/30.
 */

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-30（创建日期）
 * @Description:
 * 用来抛出自定义异常
 */
public class AppException extends Exception{
    private static final long serialVersionUID = -8999932578270387947L;
    //Http状态码
    Integer status;
    //自定义错误代码，用来给用户显示
    int code;
    //错误链接，指向错误位置
    String link;
    //错误信息
    String errorMessage;
    public AppException(){

    }
    public AppException(Integer status, int code, String link, String errorMessage,String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.link = link;
        this.errorMessage = errorMessage;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        errorMessage = message;
    }
}
