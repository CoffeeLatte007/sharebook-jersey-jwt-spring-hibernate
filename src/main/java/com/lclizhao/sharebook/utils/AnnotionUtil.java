package com.lclizhao.sharebook.utils;

import com.lclizhao.sharebook.resource.Exception.AppException;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lizhaoz on 2015/12/9.
 */

public class AnnotionUtil {
    public static void checkAnnotionResource(FormDataContentDisposition disposition, String content, String page_no, String digest) throws AppException{
        if (digest==null){
            if(disposition==null){
                throw new AppException(400,4001,"link","no digest","no digest");
            }
            if(disposition.getSize()>3*1024*1024){
                throw new AppException(400,4002,"link","the size limit 3m","the size limit 3m");
            }
            String filename=disposition.getFileName();
            String prifix=filename.substring(filename.lastIndexOf(".")+1);
            //只支持jpg,jpeg,和Png
            if (!(prifix.equals("jpg")||prifix.equals("jpeg")||prifix.equals("png"))){
                throw new AppException(400,4003,"link","the picture just jpg jpeg png","the picture just jpg jpeg png");
            }

        }
        else{
                if(digest.length()>100){
                    throw new AppException(400,4004,"link","the digest size is  too long","the digest size is too long ");
                }
        }
        //content必须大于15，小于1000
        if (content.length()<15){
            throw new AppException(400,4005,"link","the content size is too short","the content size is too short ");
        }
        if (content.length()>1000){
            throw new AppException(400,4006,"link","the content size is too long","the content size is too long ");
        }
        //页码长度必须为6位正整数,这里用正则表达式判断
        Pattern p = Pattern.compile("[0-9]{1,6}");//页码

        Matcher m = p.matcher(page_no);
        if (!m.matches()){
            throw new AppException(400,4007,"link","the page incorrect","the page incorrect");
        }
    }

}
